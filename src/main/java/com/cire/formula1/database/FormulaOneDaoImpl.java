package com.cire.formula1.database;

import com.cire.formula1.database.entity.RaceSessionEntity;
import com.cire.formula1.database.repository.RaceSessionEntityRepository;
import com.cire.formula1.model.RaceSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.Optional;

@Service
public class FormulaOneDaoImpl implements FormulaOneDao {

    private RaceSessionEntityRepository raceSessionRepo;

    @Autowired
    public FormulaOneDaoImpl(RaceSessionEntityRepository raceSessionRepo) {
        this.raceSessionRepo = raceSessionRepo;
    }

    @Override
    public RaceSessionEntity createRaceSession(RaceSession session) {
        return raceSessionRepo.save(new RaceSessionEntity(session));
    }

    @Override
    public Optional<RaceSessionEntity> getRaceSessionByUid(BigInteger sessionUid) {
        return raceSessionRepo.findById(sessionUid.longValue());
    }
}
