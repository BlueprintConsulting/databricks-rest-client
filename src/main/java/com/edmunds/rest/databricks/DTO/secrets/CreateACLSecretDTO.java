package com.edmunds.rest.databricks.DTO.secrets;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateACLSecretDTO {

    private String scope;
    private String principal;
    private ACLPermission permission;

}
