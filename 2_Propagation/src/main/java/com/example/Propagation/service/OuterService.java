package com.example.Propagation.service;

import com.example.Propagation.service.InnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OuterService {

    private final InnerService inner;

    @Transactional(propagation = Propagation.REQUIRED)
    public void callRequired(String owner) {
        inner.required(owner);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void callSupports(String owner) {

        inner.supports(owner);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void callMandatory(String owner) {

        inner.mandatory(owner);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void callRequiresNew(String owner) {
        inner.requiresNew(owner);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void callNotSupported(String owner) {
        inner.notSupported(owner);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void callNever(String owner) {
        inner.never(owner);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void callNested(String owner) {
        inner.nested(owner);
    }
}
