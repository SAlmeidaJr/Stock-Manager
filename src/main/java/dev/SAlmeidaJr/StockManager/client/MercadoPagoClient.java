package dev.SAlmeidaJr.StockManager.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.mercadopago.MercadoPagoConfig;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class MercadoPagoClient {

    @Value("${mercado_pago.access_token}")
    private String acessToken;

    @Value("${mercado_pago.notification_url}")
    private String notificationUrl;

    public void init(){
        MercadoPagoConfig.setAccessToken(acessToken);
        log.info("iniciando Mercado Pago");
    }


}
