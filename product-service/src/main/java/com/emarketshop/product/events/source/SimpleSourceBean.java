// package com.emarketshop.product.events.source;

// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.cloud.stream.messaging.Source;
// import org.springframework.messaging.support.MessageBuilder;
// import org.springframework.stereotype.Component;

// import com.emarketshop.product.constant.ActionEnum;
// import com.emarketshop.product.events.model.ProductChangeModel;
// import com.emarketshop.product.utils.UserContext;

// @Component
// public class SimpleSourceBean {
//     private Source source;

//     private static final Logger logger = LoggerFactory.getLogger(SimpleSourceBean.class);

//     @Autowired
//     public SimpleSourceBean(Source source){
//         this.source = source;
//     }

//     public void publishProductChange(ActionEnum action, String productId){
//        logger.debug("Sending Kafka message {} for Product Id: {}", action, productId);
//         ProductChangeModel change =  new ProductChangeModel(
//                 ProductChangeModel.class.getTypeName(),
//                 action.toString(),
//                 productId,
//                 UserContext.getCorrelationId());

//         source.output().send(MessageBuilder.withPayload(change).build());
//     }
// }
