package org.koreait.planitkorea.repository;

import org.koreait.planitkorea.entity.Board;
import org.koreait.planitkorea.entity.BoardType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
    List<Board> findByBoardType(BoardType boardType);
}
