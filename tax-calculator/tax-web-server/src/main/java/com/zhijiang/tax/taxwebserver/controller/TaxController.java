package com.zhijiang.tax.taxwebserver.controller;

import com.zhijiang.tax.TaxCalculatorGrpc;
import com.zhijiang.tax.TaxResponse;
import com.zhijiang.tax.taxwebserver.payload.TaxRequest;
import io.grpc.Channel;
import net.devh.springboot.autoconfigure.grpc.client.GrpcClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaxController {
    @GrpcClient("tax-service")
    private Channel channel;

    @RequestMapping(method = RequestMethod.POST, path = "/tax")
    public String calcTax(@RequestBody TaxRequest request) {
        double income = request.getIncome();
        double deduction = request.getDeduction();

        com.zhijiang.tax.TaxRequest  taxRequest = com.zhijiang.tax.TaxRequest.newBuilder().setIncome(income)
                .setDeduction(deduction).build();

        TaxCalculatorGrpc.TaxCalculatorBlockingStub stub = TaxCalculatorGrpc.newBlockingStub(channel);
        TaxResponse response = stub.calc(taxRequest);

        return String.format("%.2f", response.getTax());
    }
}
