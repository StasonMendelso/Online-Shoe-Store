package com.shoe.store.validation.validator;

import org.springframework.stereotype.Component;

/**
 * @author Stanislav Hlova
 */
@Component
public class PageValidator extends Validator<Integer> {

  @Override
  public boolean isValid(Integer page) {
    return page > 0;
  }
}
