package com.lyzd.om.spring.common.event.messaging.rabbit;

import org.springframework.validation.annotation.Validated;

import lombok.Data;

/**
 * @author Thinker
 *
 */
@Data
//@Component
//@ConfigurationProperties(prefix = "operationmanagerabbit")
//@ConfigurationProperties("operationmanagerabbit")
@Validated
public class OMRabbitProperties {
    //@NotBlank
    private String publishx="user-publish-x";

    //@NotBlank
    private String publishdlx="user-publish-dlx";

    //@NotBlank
    private String publishdlq="user-publish-dlq";

    //@NotBlank
    private String receiveq="user-receive-q";

    //@NotBlank
    private String receivedlx="user-receive-dlx";

    //@NotBlank
    private String receivedlq="user-receive-dlq";

    //@NotBlank
    private String receiveRecoverx="user-receive-recover-x";

}
