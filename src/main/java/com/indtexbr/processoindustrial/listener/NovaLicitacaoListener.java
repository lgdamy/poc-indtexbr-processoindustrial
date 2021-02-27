package com.indtexbr.processoindustrial.listener;

import com.indtexbr.processoindustrial.configuration.RabbitConfig;
import com.indtexbr.processoindustrial.domain.dto.ListingDTO;
import com.indtexbr.processoindustrial.domain.dto.ProposalDTO;
import com.indtexbr.processoindustrial.service.ProposalService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Random;

/**
 * @author lgdamy@raiadrogasil.com on 25/02/2021
 */
@Component
public class NovaLicitacaoListener {

    @Autowired
    private ProposalService service;

    @RabbitListener(containerFactory = "rabbitListener", queues = RabbitConfig.LICITACAO)
    public void listen(@Payload ListingDTO listing) {
        try {
            Random random = new Random();
            int propostas = random.nextInt(5) + 1;
            while (propostas-- > 0) {
                Thread.sleep(random.nextInt(28000) + 2000L); //de 2 a 30 segundos
                this.gerarProposta(listing.getId());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void gerarProposta(Long idLicitacao) {
        Random random = new Random();
        Empresa e = Empresa.values()[random.nextInt(Empresa.values().length)];
        ProposalDTO proposal = new ProposalDTO();
        proposal.setListing(idLicitacao);
        proposal.setCompany(e.nome);
        proposal.setCompanyDocument(e.documento);
        proposal.setPrice(BigDecimal.valueOf(random.nextInt(2000000) + 100, 2)); // de 1 a 20mil
        proposal.setProductionTime(random.nextInt(60));
        proposal.setShippingTime(random.nextInt(60));
        service.create(proposal);
    }

    @AllArgsConstructor
    private enum Empresa {
        E1("Tecidos Charles", "30281367000191"),
        E2("Bronx Panos", "2891882798001"),
        E3("Earl Twirl", "1839918371933"),
        E4("Giuliani Binni Modas", "18928791872213"),
        E5("Sinopec Group", "w19uu3uu122239woi129"),
        E6("Rosset", "78398179282241121"),
        E7("Sancris Linhas", "53161361000132"),
        E8("Santanense", "13556133368600812"),
        E9("Cedro Têxtil", "N/A"),
        E10("Malhas RVB", "89817263633312"),
        E11("SantaConstancia", "1555136733611"),
        E12("La Estampa Tecidos & Artes", "77355711479553"),
        E13("Linhas Trichê", "355136899435"),
        E14("Hudtelfa", "2237996635511"),
        E15("Santista", "1166643673331"),
        E16("Paramount", "133688851124"),
        E17("Indústria Denim Canatiba", "88155822583"),
        E18("Manequins Expor", "11261379575552"),
        E19("Vicunha Têxtil", "122416442758"),
        E20("Nicoletti Têxtil", "366333136954452"),
        E21("Incotex", "11127858345133"),
        E22("Salotex", "13351777471133");

        private String nome;
        private String documento;
    }
}
