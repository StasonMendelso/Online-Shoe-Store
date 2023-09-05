package com.shoe.store.validation.validator;

/**
 * @author Stanislav Hlova
 */
public abstract class Validator<T> {
  abstract public boolean isValid(T value);
}
