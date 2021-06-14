package com.healthbyte.service;

import com.healthbyte.model.Visitor;

import java.util.List;
import java.util.Map;

public interface VisitorService {

    Visitor getVisitorById(Integer id);
    Visitor saveVisitor(Visitor visitor);
    List<Visitor> findAllVisitor();
    Map<String, Boolean> deleteVisitor(Integer id);
    Map<String, Boolean> Visitor(Integer id);

    Visitor updateVisitor(Integer id, Visitor visitor);

}
