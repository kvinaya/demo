/**
 * UserRepository.java
 */
package com.vk;
import org.springframework.data.jpa.repository.JpaRepository;

interface UserRepository extends JpaRepository<User, Long> {


}
