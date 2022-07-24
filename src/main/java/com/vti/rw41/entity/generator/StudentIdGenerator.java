package com.vti.rw41.entity.generator;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;
import java.time.Year;

public class StudentIdGenerator implements IdentifierGenerator {

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object o) throws HibernateException {

        session.createNativeQuery("update studentseq set next_val = next_val + 1")
                .executeUpdate();

        Number nextVal = (Number) session.createNativeQuery("select next_val from studentSeq")
                .getSingleResult();

        String stt = String.format("%05d", nextVal.intValue());
        return "S" + Year.now() + stt;
    }
}
