package com.prueba.repositories;

import com.prueba.entities.Client;
import jakarta.persistence.Tuple;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClientRepo extends GenericRepo<Client, Long> {

    @Query(value = """
        select c.first_name, c.last_name, c.identification, c.age, c.email, a.account_number, a.created_date
        from clients c
        join accounts a on c.client_id = a.client_id
        order by c.registration_date desc
    """, nativeQuery = true)
    List<Tuple> getClientsAccounts();

}
