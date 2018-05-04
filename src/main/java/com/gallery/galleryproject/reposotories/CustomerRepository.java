package com.gallery.galleryproject.reposotories;

import com.gallery.galleryproject.models.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
}



