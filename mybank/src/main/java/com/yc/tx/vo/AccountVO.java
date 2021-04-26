package com.yc.tx.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @program: springboot
 * @description:
 * @author: 作者
 * @create: 2021-04-24 20:40
 */
@Data
public class AccountVO implements Serializable {
    private static final long serialVersionUID = -7434767872348659321L;
    private Integer accountId;
    private Double money;
    private Integer inAccountId;
}
