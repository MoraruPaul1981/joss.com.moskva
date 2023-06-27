package com.dsy.dsu.AllDatabases.modelORM;



import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.NotNull;

import java.io.Serializable;


@Entity(indexes = { @Index(value = "customer_id,item_id", unique = true)})
public class Review {
    @Id(autoincrement = true)
    private Long localID;
    private String customer_id;
    private String item_id;
    @NotNull
    private Integer star_rating;
    private String content;
    public Review() {}
}

