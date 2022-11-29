package com.pawn_shop.service.impl;

import com.pawn_shop.model.pawn.PawnType;
import com.pawn_shop.repository.IPawnTypeRepository;
import com.pawn_shop.service.IPawnTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PawnTypeService implements IPawnTypeService {


    @Autowired
    private IPawnTypeRepository iPawnTypeRepository;

    @Override
    public List<PawnType> findAllPawnType() {
        return iPawnTypeRepository.findAllPawnType();

    }

    @Override
        public <T > List < T > findAllPawnType(Class < T > tClass) {
            return iPawnTypeRepository.findAllPawnTypeLiquidation(tClass);
        }

    }
