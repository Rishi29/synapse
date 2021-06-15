package com.healthbyte.service.impl;

import com.healthbyte.exception.VisitorNotFoundException;
import com.healthbyte.model.Visitor;
import com.healthbyte.repository.VisitorRepository;
import com.healthbyte.service.VisitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class VisitorServiceImpl implements VisitorService  {

    @Autowired
    private VisitorRepository visitorRepository;

    public VisitorServiceImpl(VisitorRepository visitorRepository) {
        this.visitorRepository = visitorRepository;
    }

    public Visitor getVisitorById(Integer id) {
        Optional<Visitor> visitor = visitorRepository.findById(id);
       if(visitor.isPresent()){
           return visitor.get();
       };
        throw new VisitorNotFoundException("Vistor not found with this id "+id);
    }

    @Override
    public Visitor saveVisitor(Visitor visitor) {
        return visitorRepository.save(visitor);

    }



    @Override
    public List<Visitor> findAllVisitor() {
        return  visitorRepository.findAll();
    }

    @Override
    public Map<String, Boolean> deleteVisitor(Integer id) {

        Map<String, Boolean> response = new HashMap<>();
       Optional<Visitor> visitor =  visitorRepository.findById(id);
       if(visitor.isPresent()){
           visitorRepository.deleteById(id);
       }else{
           throw new VisitorNotFoundException("Visitor Not found with this ");
       }
       response.put("Deleted", Boolean.TRUE);
        return response;
    }

    @Override
    public Visitor updateVisitor(Integer id, Visitor visitorDetails) {
        Optional<Visitor> visitorById = visitorRepository.findById(id);
        if(visitorById.isPresent()){
            Visitor visitor = visitorById.get();
            visitor.setId(visitorDetails.getId());
            visitor.setName(visitorDetails.getName());
            visitor.setEmailId(visitorDetails.getEmailId());

           return  visitorRepository.save(visitor);

        }else{
            throw new VisitorNotFoundException("Visitor not found with the given id  "+ id);
        }
    }
}
