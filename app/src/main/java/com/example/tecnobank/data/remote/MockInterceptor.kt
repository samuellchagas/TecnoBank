package com.example.tecnobank.data.remote

import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Response
import okhttp3.ResponseBody

class MockInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
            val uri = chain.request().url.toUri().toString()
            val responseString = when {
                uri.contains("login") -> getListOfReponseLoginJson()
                uri.contains("home") -> getListOfReponseHomeJson()
                uri.contains("extract") -> getListOfReponseExtractJson()
                uri.contains("validation") -> getListOfReponseValidationJson()
                uri.contains("confirm") -> getListOfReponseConfirmJson()
                else -> throw Exception("request Invalida")
            }

            return chain.proceed(chain.request())
                .newBuilder()
                .code(200)
                .message(responseString)
                .body(
                    ResponseBody.create(
                        "application/json".toMediaTypeOrNull(),
                        responseString.toByteArray())
                )
                .build()
    }
}

private fun getListOfReponseLoginJson():String{
    return """
        {
        "token":"AAAAAAAA",
        "user":
        {
            "firstName":"Samuell",
            "lastName":"Chagas"
        }
        }
    """
}

private fun getListOfReponseHomeJson():String{
    return """
       {
       "balance":{
           "current_value":1042.55,
           "receivables":0
       },
       "benefits":[
           {
           "indicator_color":"#FFEB3B",
           "image":"https://assets.pagseguro.com.br/ps-website-assets/v14.44.0/img/_pswn/generic/large/deposito-pagbank.png",
           "title":"Maquininhas PagSeguro",
           "message":"Venda muito em até 18x e receba tudo na hora. só no PagSeguro Pagbank!",
           "text_link":"Peça já!"
           },
           {
           "indicator_color":"#4CAF50",
           "image":"https://assets.pagseguro.com.br/ps-website-assets/v14.44.0/img/_pswn/generic/large/recarga.png",
           "title":"Recargas de Celular",
           "message":"2% de volta em todas as Recargas de Celular com saldo em conta ou cartão de crédito",
           "text_link":"Saiba mais"
           },
           {
           "indicator_color":"#FFEB3B",
           "image":"https://assets.pagseguro.com.br/ps-website-assets/v14.44.0/img/_pswn/generic/large/pagamento-contas-cartao-credito.png",
           "title":"Pague com o credito",
           "message":"Pague contas e boletos parcelados com seu cartão de crédito!",
           "text_link":"Saiba mais"
           },
           {
           "indicator_color":"#FFEB3B",
           "image":"https://assets.pagseguro.com.br/ps-website-assets/v14.44.0/img/_pswn/generic/large/aponte-e-pag-pro2.png",
           "title":"Receba com QR Code",
           "message":"Receba e realize pagamentos com QR Code",
           "text_link":"Saiba mais"
           }]
       } 
    """
}

private fun getListOfReponseExtractJson():String{
    return """
        [
        {
            "status":"completed",
            "time":"04:08",
            "type_description":"Pix",
            "type":"Pagamento",
            "value":6,
            "date":"23/08/2021"
        },
        {
            "status":"completed",
            "time":"05:08",
            "type_description":"Pix",
            "type":"Pagamento",
            "value":5.55,
            "date":"18/08/2021"
        },
        {
            "status":"completed",
            "time":"06:08",
            "type_description":"Pix",
            "type":"Despesa",
            "value":10.00,
            "date":"21/08/2021"
        }
        ]
    """
}

private fun getListOfReponseValidationJson():String{
    return """
        {
            "user":
            {
                "first_name":"Samuell",
                "last_name":"Chagas"
            },
            "pix":"samuell@pbpuc.com",
            "description":"",
            "pix_value":10000,
            "organization":"PBPUC CompassoUol",
            "date":"25/08/2021",
            "pix_token":"BBBBBBBB"
        }
    """
}

private fun getListOfReponseConfirmJson():String{
    return """
        {
        "pix":"samuell@pbpuc.com",
        "description":"",
        "pix_value":10000,
        "date":"25/08/2021 - 01:26",
        "user":
        {
            "first_name":"Samuell",
            "last_name":"Chagas"
        },
        "organization":"PBPUC CompassoUol"
        }
    """
}