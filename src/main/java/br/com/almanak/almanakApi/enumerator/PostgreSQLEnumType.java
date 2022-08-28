package br.com.almanak.almanakApi.enumerator;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.type.EnumType;

public class PostgreSQLEnumType extends EnumType {

    // https://prateek-ashtikar512.medium.com/how-to-map-java-enum-to-postgresql-enum-type-fcb3f81a7c42

    @Override
    public void nullSafeSet(PreparedStatement ps, Object obj, int index,
            SharedSessionContractImplementor session) throws HibernateException, SQLException {        
        if (obj == null) {
            ps.setNull(index, Types.OTHER);
        } else {             
            ps.setObject(index, obj.toString(), Types.OTHER);
        }
    }
}