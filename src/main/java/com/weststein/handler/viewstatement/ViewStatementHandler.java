package com.weststein.handler.viewstatement;

import com.weststein.controller.secured.model.ViewStatementModel;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ViewStatementHandler extends AbstractViewStatementHandler<ViewStatementModel> {

    @Override
    public ViewStatementModel handle(Long id, Long cardholderId, LocalDateTime from, LocalDateTime to, TransactionType type, int size, int page) {
        ViewStatementModel model = getStatement(cardholderId, from, to, type, size, page);
        return model;
    }

}
