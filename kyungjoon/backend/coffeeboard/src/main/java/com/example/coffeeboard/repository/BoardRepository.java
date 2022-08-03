package com.example.coffeeboard.repository;

import com.example.coffeeboard.entity.Board;
import org.springframework.data.repository.CrudRepository;
import java.util.ArrayList;

public interface BoardRepository extends CrudRepository <Board, Long> {
    @Override
    ArrayList<Board> findAll();
}
