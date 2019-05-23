package com.epam.training.sportsbetting.ui;

import com.epam.training.sportsbetting.domain.Player;
import java.math.BigDecimal;

public interface IO {
    Player readPlayerData();
    BigDecimal readWagerAmount();
}
