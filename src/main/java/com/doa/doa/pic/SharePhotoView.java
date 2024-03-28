package com.doa.doa.pic;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route("pic/share")
@PageTitle(("사진 공유"))
public class SharePhotoView extends VerticalLayout {
    public SharePhotoView(){
        add(new Text("사진 공유 기능이 구현됩니다."));
    }
}
