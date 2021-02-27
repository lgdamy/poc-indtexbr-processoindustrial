package com.indtexbr.processoindustrial.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.MDC;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author lgdamy@raiadrogasil.com on 25/02/2021
 */
@EnableRabbit
@Configuration
public class RabbitConfig {

    public static final String LICITACAO = "licitacao.criada";
    public static final String ORCAMENTO = "orcamento.criado";
    public static final String TOPIC = "amq.topic";


    @Autowired
    private ConnectionFactory factory;

    @Autowired
    private ObjectMapper mapper;

    @Bean
    public Queue criarLicitacao() {
        return QueueBuilder.durable(LICITACAO).build();
    }

    @Bean
    public TopicExchange criarOrcamentoExchange() {
        return new TopicExchange(TOPIC);
    }

    @Bean
    public RabbitTemplate criaLicitacaoTemplate() {
        RabbitTemplate template = new RabbitTemplate(factory);
        template.setMessageConverter(new Jackson2JsonMessageConverter(mapper));
        template.setExchange(DirectExchange.DEFAULT.getName());
        template.setRoutingKey(LICITACAO);
        return template;
    }

    @Bean
    public RabbitTemplate criaOrcamentoTemplate() {
        RabbitTemplate template = new RabbitTemplate(factory);
        template.setMessageConverter(new Jackson2JsonMessageConverter(mapper));
        template.setExchange(TOPIC);
        template.setRoutingKey(ORCAMENTO);
        return template;
    }

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListener() {
        SimpleRabbitListenerContainerFactory connFactory = new SimpleRabbitListenerContainerFactory();
        connFactory.setConnectionFactory(this.factory);
        connFactory.setMessageConverter(new Jackson2JsonMessageConverter(mapper));
        connFactory.setAutoStartup(true);
        connFactory.setMaxConcurrentConsumers(1);
        connFactory.setConcurrentConsumers(1);
        connFactory.setMissingQueuesFatal(false);
        connFactory.setStartConsumerMinInterval(3000L);
        connFactory.setRecoveryInterval(15000L);
        connFactory.setChannelTransacted(true);
        connFactory.setPrefetchCount(1);
        return connFactory;
    }

}
