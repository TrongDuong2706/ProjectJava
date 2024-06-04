package com.cyphersoft.osahaneat.service.imp;

import com.cyphersoft.osahaneat.payload.request.OrderRequest;

public interface OrderServiceImp {
    boolean insertOrder(OrderRequest orderRequest);
}
