package chess.dao;

import chess.domain.Color;

public interface BoardDao {

    void save(Color turn);

    Color findTurn();

    void deleteBoard();
}
