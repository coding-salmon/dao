package com.doa.doa.pic;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("pic/ranking")
public class RankingView extends VerticalLayout {

    public RankingView(){
        //랭킹 기능 UI 구성
        add(new Text("랭킹 기능이 구현됩니다."));
        add(new Button("나가기", e -> getUI().ifPresent(ui -> ui.navigate(""))));
    }
}
