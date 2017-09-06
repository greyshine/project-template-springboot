package de.greyshine.springboottemplate.jpa;

import org.springframework.data.repository.CrudRepository;


public interface EntryCrudRepository extends CrudRepository<Entry, Long> {

}
