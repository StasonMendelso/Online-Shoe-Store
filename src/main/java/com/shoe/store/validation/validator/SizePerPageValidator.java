package com.shoe.store.validation.validator;

import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @author Stanislav Hlova
 */
@Component
public class SizePerPageValidator extends Validator<Integer> {

  @Override
  public boolean isValid(Integer sizePerPage) {
    if(Objects.isNull(sizePerPage)){
      return false;
    }
    return sizePerPage > 0;
  }
}
