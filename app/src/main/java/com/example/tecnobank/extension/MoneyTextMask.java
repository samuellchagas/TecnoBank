package com.example.tecnobank.extension;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.lang.ref.WeakReference;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class MoneyTextMask implements TextWatcher {
    private final WeakReference<EditText> editTextWeakReference;
    private final Locale locale = new Locale("pt","BR");

    public MoneyTextMask(EditText editText) {
        this.editTextWeakReference = new WeakReference<>(editText);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        EditText editText = editTextWeakReference.get();
        if (editText == null) return;
        editText.removeTextChangedListener(this);

        BigDecimal parsed = parseToBigDecimal(editable.toString());
        String formatted = NumberFormat.getCurrencyInstance(locale).format(parsed);
        //Remove o símbolo da moeda e espaçamento pra evitar bug
        String replaceable = String.format("[%s\\s]", getCurrencySymbol(locale));
        String cleanString = formatted.replaceAll(replaceable, "");

        editText.setText(formatted);
        editText.setSelection(formatted.length());
        editText.addTextChangedListener(this);
    }

    private BigDecimal parseToBigDecimal(String value) {
        String replaceable = String.format("[%s,.\\s]", getCurrencySymbol(locale));

        String cleanString = value.replaceAll(replaceable, "");

        try {
            return new BigDecimal(cleanString).setScale(
                2, BigDecimal.ROUND_FLOOR).divide(new BigDecimal(100), BigDecimal.ROUND_FLOOR);
        } catch (NumberFormatException e) {
            //ao apagar todos valores de uma só vez dava erro
            //Com a exception o valor retornado é 0.00
            return new BigDecimal(0);

        }
    }

    public static String getCurrencySymbol(Locale locale) {

        return NumberFormat.getCurrencyInstance(locale).getCurrency().getSymbol();

    }
}
