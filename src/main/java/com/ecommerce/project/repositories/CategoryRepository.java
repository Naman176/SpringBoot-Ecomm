package com.ecommerce.project.repositories;

import com.ecommerce.project.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}

// We get two repository interfaces for us from those libraries that we have configured like the dependencies that we
// have added. And the 1st repository is Crud repository. It's coming in from org dot spring framework dot data dot
// repository. So this is one interface that provides Crud functions like save find one, find all count delete.
// So all these operations you can do with the help of this particular repository interface. And this interface is very
// useful because it comes with a bunch of generic Crud operations, which means that you get Crud operations readymade
// for any entity for free, you don't have to write a single line of code. You just create a repository interface and
// extend it with Crud repository.
// But we are not going to make use of this. We are going to make use of JPA repository.
//
// So this is also so you can see over your JPA repository. This is the extension of crud repository but it also adds few
// more methods that are GPS specific, like flash save and flash delete in batch and all.
// In general, if you're working with JPA, this is a better choice as compared to Crud repository,
// We need to specify those two parameters. Those two parameters have to be the type of the entity and the type of the
// primary key of the entity.
// And one of the greatest thing about this is that we don't have to write any implementation code for this particular
// repository that we have created. Spring data JPA will automatically generate the implementation at the runtime, and
// we can use it to perform all the basic Crud operations on the category table.
//So for now we have created the repository and we have extended it with JPA repository interface.