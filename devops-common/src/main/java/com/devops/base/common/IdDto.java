package com.devops.base.common;

import java.io.Serializable;

public class IdDto implements Serializable {

    private static final long serialVersionUID = 8739655111378316905L;

    private Integer supplierId;

    private Long productId;

    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

}
