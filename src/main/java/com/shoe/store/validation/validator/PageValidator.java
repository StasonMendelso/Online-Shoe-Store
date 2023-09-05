package com.shoe.store.validation.validator;

import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @author Stanislav Hlova
 */
@Component
public class PageValidator extends Validator<Integer> {

  @Override
  public boolean isValid(Integer page) {
    if(Objects.isNull(page)){
      return false;
    }
    return page > 0;
  }
}
