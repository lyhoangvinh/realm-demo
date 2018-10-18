package com.lyhoangvinh.realmbookexample.paths.books;

 import android.content.Context;

import com.lyhoangvinh.realmbookexample.application.RealmManager;
import com.lyhoangvinh.realmbookexample.data.entity.Book;
import com.lyhoangvinh.realmbookexample.data.entity.BookFields;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;


public class BooksPresenter {
    public static final String TAG = "BooksPresenter";
    public static BooksPresenter getService(Context context) {
        //noinspection ResourceType
        return (BooksPresenter) context.getSystemService(TAG);
    }



    public interface ViewContract {
        void showAddBookDialog();

        void showMissingTitle();

        void showEditBookDialog(Book book);

        interface DialogContract {
            String getTitle();

            String getAuthor();

            String getThumbnail();

            void bind(Book book);
        }
    }

    ViewContract viewContract;

    boolean isDialogShowing;

    boolean hasView() {
        return viewContract != null;
    }

    public void bindView(ViewContract viewContract) {
        this.viewContract = viewContract;
        if (isDialogShowing) {
            showAddDialog();
        }
    }

    public void unbindView() {
        this.viewContract = null;
    }

    public void showAddDialog() {
        if (hasView()) {
            isDialogShowing = true;
            viewContract.showAddBookDialog();
        }
    }

    public void dismissAddDialog() {
        isDialogShowing = false;
    }

    public void showEditDialog(Book book) {
        if (hasView()) {
            viewContract.showEditBookDialog(book);
        }
    }

    public void saveBook(ViewContract.DialogContract dialogContract) {
        if (hasView()) {
            final String author = dialogContract.getAuthor();
            final String title = dialogContract.getTitle();
            final String thumbnail = dialogContract.getThumbnail();

            if (title == null || "".equals(title.trim())) {
                viewContract.showMissingTitle();
            } else {
                Realm realm = RealmManager.getRealm();
                realm.executeTransactionAsync(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        Book book = new Book();
                        long id = 1;
                        if (realm.where(Book.class).count() > 0) {
                            id = realm.where(Book.class).max(BookFields.ID).longValue() + 1; // auto-increment id
                        }
                        book.setId(id);
                        book.setAuthor(author);
                        book.setDescription("");
                        book.setImageUrl(thumbnail);
                        book.setTitle(title);
                        realm.insertOrUpdate(book);
                    }
                });
            }
        }
    }

    public void saveBook() {
        Realm realm = RealmManager.getRealm();
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.insertOrUpdate(listBook());
            }
        });
    }

    private List<Book> listBook() {
        List<Book> list = new ArrayList<>();

        Book book1 = new Book();
        book1.setId(1);
        book1.setAuthor("Reto Meier");
        book1.setTitle("Android 4 Application Development");
        book1.setImageUrl("https://static.spankbang.com/pornstarimg/f/684-250.jpg");

        Book book2 = new Book();
        book2.setId(2);
        book2.setAuthor("Itzik Ben-Gan");
        book2.setTitle("Microsoft SQL Server 2012 T-SQL Fundamentals");
        book2.setImageUrl("http://www.japanesethumbs.com/jav/ai-uehara/79/ai-uehara-8.jpg");

        Book book3 = new Book();
        book3.setId(3);
        book3.setAuthor("Magnus Lie Hetland");
        book3.setTitle("Beginning Python: From Novice To Professional Paperback");
        book3.setImageUrl("https://i.imgur.com/ji8Y1Z9.jpg");

        Book book4 = new Book();
        book4.setId(4);
        book4.setAuthor("Chad Fowler");
        book4.setTitle("The Passionate Programmer: Creating a Remarkable Career in Software Development");
        book4.setImageUrl("https://images2-focus-opensocial.googleusercontent.com/gadgets/proxy?container=focus&gadget=a&no_expand=1&refresh=604800&url=http://javhd.pro/data/07-22-16_ebod-386.jpg");

        Book book5 = new Book();
        book5.setId(5);
        book5.setAuthor("Yashavant Kanetkar");
        book5.setTitle("Written Test Questions In C Programming");
        book5.setImageUrl("https://ci.phncdn.com/pics/pornstars/000/247/101/(m=lciuhScOb_c)(mh=KFW6tTvX4graRxpP)thumb_142221.jpg");

        list.add(book1);
        list.add(book2);
        list.add(book3);
        list.add(book4);
        list.add(book5);
        return list;
    }

    public void deleteBookById(final long id) {
        Realm realm = RealmManager.getRealm();
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Book book = realm.where(Book.class).equalTo(BookFields.ID, id).findFirst();
                if (book != null) {
                    book.deleteFromRealm();
                }
            }
        });
    }

    public void editBook(final ViewContract.DialogContract dialogContract, final long id) {
        Realm realm = RealmManager.getRealm();
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Book book = realm.where(Book.class).equalTo(BookFields.ID, id).findFirst();
                if (book != null) {
                    book.setTitle(dialogContract.getTitle());
                    book.setImageUrl(dialogContract.getThumbnail());
                    book.setAuthor(dialogContract.getAuthor());
                }
            }
        });
    }
}
