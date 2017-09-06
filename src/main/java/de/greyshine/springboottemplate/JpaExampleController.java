package de.greyshine.springboottemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import de.greyshine.springboottemplate.jpa.Entry;
import de.greyshine.springboottemplate.jpa.EntryCrudRepository;

@RestController
@Repository
public class JpaExampleController {
	
	//@PersistenceContext
	//private EntityManager entityManager;#
	
	// https://spring.io/guides/gs/accessing-data-mysql/
	
	@Autowired
	EntryCrudRepository r;

	@PostMapping(  path="/jpa/post" )
	public void post() {
		
		Entry e = new Entry();
		e.setText( "works" );
		
		r.save( e );
		
		System.out.println( "OK" );
		
		for( Entry e2 : r.findAll() ) {
			System.out.println( "O2K: "+ e2 );
		}
	}

	
}
