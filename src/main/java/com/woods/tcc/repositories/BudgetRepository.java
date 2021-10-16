package com.woods.tcc.repositories;

import com.woods.tcc.model.Budget;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BudgetRepository extends JpaRepository<Budget, Long>{

}
