package com.testProject.springboot.testTask.dto;

import com.testProject.springboot.testTask.entity.CheckoutOrder;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Request {

    private CheckoutOrder checkoutOrder;
}
