package com.knoldus.knolteria;

import com.google.common.collect.ImmutableMap;
import graphql.schema.DataFetcher;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Component
public class GraphQLDataFetchers {

    private static List<Map<String, String>> menu = Arrays.asList(
            ImmutableMap.of("id", "order-1",
                    "name", "Butter Chicken",
                    "veg", "No",
                    "paymentID","payment-1"),
            ImmutableMap.of("id", "order-2",
                    "name", "Shahi Paneer",
                    "veg", "Yes",
                    "paymentID","payment-2"),
            ImmutableMap.of("id", "order-3",
                    "name", "Chicken Lollipop",
                    "veg", "No",
                    "paymentID","payment-3")
    );

    private static List<Map<String, String>> payment = Arrays.asList(
            ImmutableMap.of("id", "payment-1",
                    "price", "220",
                    "status", "Done"),
            ImmutableMap.of("id", "payment-2",
                    "price", "200",
                    "status", "Done"),
            ImmutableMap.of("id", "payment-3",
                    "price", "240",
                    "status", "Pending")
            );

    public DataFetcher getOrderByIdDataFetcher() {
        return dataFetchingEnvironment -> {
            String orderId = dataFetchingEnvironment.getArgument("id");
            return menu
                    .stream()
                    .filter(book -> book.get("id").equals(orderId))
                    .findFirst()
                    .orElse(null);
        };
    }

    public DataFetcher getPaymentByIdDataFetcher(){
        return dataFetchingEnvironment -> {
            Map<String,String> order = dataFetchingEnvironment.getSource();
            String paymentID = order.get("paymentID");
            return payment
                    .stream()
                    .filter(payment -> payment.get("id").equals(paymentID))
                    .findFirst()
                    .orElse(null);
        };
    }
}