package com.zhijiang.tax.taxservice.service;

import com.zhijiang.tax.TaxCalculatorGrpc;
import com.zhijiang.tax.TaxRequest;
import com.zhijiang.tax.TaxResponse;
import io.grpc.stub.StreamObserver;
import net.devh.springboot.autoconfigure.grpc.server.GrpcService;

@GrpcService(TaxService.class)
public class TaxService extends TaxCalculatorGrpc.TaxCalculatorImplBase{
    @Override
    public void calc(TaxRequest request, StreamObserver<TaxResponse> responseObserver) {
        double incomeShouldPayTax = request.getIncome() - request.getDeduction();

        double tax = incomeShouldPayTax * 0.15;
        TaxResponse response = TaxResponse.newBuilder().setTax(tax).build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
