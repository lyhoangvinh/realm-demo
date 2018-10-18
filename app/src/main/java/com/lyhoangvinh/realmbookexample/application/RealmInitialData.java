package com.lyhoangvinh.realmbookexample.application;

import com.lyhoangvinh.realmbookexample.data.entity.Book;

import io.realm.Realm;

/**
 * Created by vinh on 2016.08.16..
 */
public class RealmInitialData implements Realm.Transaction {
    @Override
    public void execute(Realm realm) {
        Book book = new Book();
        
        book.setId(1);
        book.setAuthor("Reto Meier");
        book.setTitle("Android 4 Application Development");
        book.setImageUrl("https://static.spankbang.com/pornstarimg/f/684-250.jpg");
        realm.insertOrUpdate(book);

        book.setId(2);
        book.setAuthor("Itzik Ben-Gan");
        book.setTitle("Microsoft SQL Server 2012 T-SQL Fundamentals");
        book.setImageUrl("http://www.japanesethumbs.com/jav/ai-uehara/79/ai-uehara-8.jpg");
        realm.insertOrUpdate(book);

        book.setId(3);
        book.setAuthor("Magnus Lie Hetland");
        book.setTitle("Beginning Python: From Novice To Professional Paperback");
        book.setImageUrl("https://i.imgur.com/ji8Y1Z9.jpg");
        realm.insertOrUpdate(book);

        book.setId(4);
        book.setAuthor("Chad Fowler");
        book.setTitle("The Passionate Programmer: Creating a Remarkable Career in Software Development");
        book.setImageUrl("https://images2-focus-opensocial.googleusercontent.com/gadgets/proxy?container=focus&gadget=a&no_expand=1&refresh=604800&url=http://javhd.pro/data/07-22-16_ebod-386.jpg");
        realm.insertOrUpdate(book);

        book.setId(5);
        book.setAuthor("Yashavant Kanetkar");
        book.setTitle("Written Test Questions In C Programming");
        book.setImageUrl("https://ci.phncdn.com/pics/pornstars/000/247/101/(m=lciuhScOb_c)(mh=KFW6tTvX4graRxpP)thumb_142221.jpg");
        realm.insertOrUpdate(book);
    }
    
    @Override
    public int hashCode() {
        return RealmInitialData.class.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return obj != null && obj instanceof RealmInitialData;
    }
}
