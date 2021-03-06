package com.hengxunda.springcloud.nio.handlers.chat.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GroupChatResponse implements Serializable {

    private static final long serialVersionUID = 5740564904734019516L;

    private String roomId;

    private String content;

    private Date createTime;
}
