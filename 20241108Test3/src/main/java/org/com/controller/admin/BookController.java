package org.com.controller.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import org.com.entity.Author;
import org.com.entity.Book;
import org.com.entity.Rating;
import org.com.service.IAuthorService;
import org.com.service.IBookService;
import org.com.service.IRatingService;
import org.com.service.impl.AuthorServiceImpl;
import org.com.service.impl.BookServiceImpl;
import org.com.service.impl.RatingServiceImpl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@MultipartConfig()
@WebServlet(
        urlPatterns = {
                "/admin/book",
                "/admin/book/add",
                "/admin/book/edit",
                "/admin/book/delete",
                "/admin/book/details"
        }
)
public class BookController extends HttpServlet {
    private static final String UPLOAD_DIR = "uploads";
    private final IBookService bookService = new BookServiceImpl();
    private final IAuthorService authorService = new AuthorServiceImpl();
    private final IRatingService ratingService = new RatingServiceImpl();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getServletPath();
        switch (action) {
            case "/admin/book/add" -> showAddForm(req, resp);
            case "/admin/book/edit" -> showEditForm(req, resp);
            case "/admin/book/delete" -> deleteBook(req, resp);
            case "/admin/book/details" -> showBookDetails(req, resp);
            default -> listBook(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getServletPath();
        switch (action) {
            case "/admin/book/add" -> addBook(req, resp);
            case "/admin/book/edit" -> updateBook(req, resp);
            default -> listBook(req, resp);
        }
    }

    private void listBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("title");
        int page = Optional.ofNullable(req.getParameter("page")).map(Integer::parseInt).orElse(1);
        int pageSize = Optional.ofNullable(req.getParameter("size")).map(Integer::parseInt).orElse(5);

        List<Book> bookList;
        int totalItems;
        if (title != null && !title.isEmpty()) {
            bookList = bookService.searchPaginated(title, page, pageSize);
            totalItems = bookService.countByTitle(title);
            req.setAttribute("title", title);
        } else {
            bookList = bookService.findAll(page, pageSize);
            totalItems = bookService.count();
        }

        int totalPages = (int) Math.ceil((double) totalItems / pageSize);
        req.setAttribute("bookList", bookList);
        req.setAttribute("currentPage", page);
        req.setAttribute("totalPages", totalPages);

        req.getRequestDispatcher("/views/admin/book-list.jsp").forward(req, resp);
    }

    private void showAddForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Author> authors = authorService.findAll();
        req.setAttribute("authors", authors);
        req.getRequestDispatcher("/views/admin/book-add.jsp").forward(req, resp);
    }

    private void showEditForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        Book existingBook = bookService.findById(id);
        List<Author> authors = authorService.findAll();
        List<Author> authors1 = authorService.findAuthorByBookId(existingBook.getId());
        req.setAttribute("book", existingBook);
        req.setAttribute("authors", authors);
        req.setAttribute("authors1", authors1);

        System.out.println("authors1" + authors1);

        req.getRequestDispatcher("/views/admin/book-edit.jsp").forward(req, resp);
    }

    private void showBookDetails(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        Book book = bookService.findById(id);

        if (book == null) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Book not found");
            return;
        }

        List<Rating> ratings = ratingService.findAllWithBookId(book.getId());
        book.setRatings(ratings);
        req.setAttribute("book", book);
        req.getRequestDispatcher("/views/admin/book-details.jsp").forward(req, resp);
    }


    private void addBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String isbn = req.getParameter("isbn");
        String title = req.getParameter("title");
        String publisher = req.getParameter("publisher");
        double price = Double.parseDouble(req.getParameter("price"));
        String description = req.getParameter("description");
        int quantity = Integer.parseInt(req.getParameter("quantity"));
        String imageLink = req.getParameter("coverImageLink");
        Part coverImagePart = req.getPart("coverImageFile");

        String[] authorIds = req.getParameterValues("authorIds[]");
        List<Author> authors = new ArrayList<>();
        if (authorIds != null) {
            for (String authorIdStr : authorIds) {
                Long authorId = Long.parseLong(authorIdStr);
                Author author = authorService.findById(authorId);
                if (author != null) {
                    authors.add(author);
                }
            }
        }

        String coverImage = "";
        String uploadPath = req.getServletContext().getRealPath("/uploads");
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists() && !uploadDir.mkdir()) {
            throw new IOException("Failed to create upload directory.");
        }

        try {
            if (coverImagePart != null && coverImagePart.getSize() > 0) {
                String fileName = Paths.get(coverImagePart.getSubmittedFileName()).getFileName().toString();
                String ext = fileName.substring(fileName.lastIndexOf(".") + 1);
                coverImage = System.currentTimeMillis() + "." + ext;
                File fileToSave = new File(uploadPath, coverImage);
                coverImagePart.write(fileToSave.getAbsolutePath());
            } else if (imageLink != null && !imageLink.isBlank()) {
                coverImage = imageLink;
            } else {
                coverImage = "default-cover.png";
            }
        } catch (Exception e) {
            e.printStackTrace();
            coverImage = "default-cover.png";
        }

        Book book = Book.builder()
                .isbn(isbn)
                .title(title)
                .publisher(publisher)
                .price(price)
                .description(description)
                .coverImage(coverImage)
                .publishDate(new Date())
                .quantity(quantity)
                .authors(authors)
                .build();

        bookService.insert(book);

        Long bookId = bookService.getLatestBookId();
        if (bookId != null) {
            for (Author author : authors) {
                bookService.insertBookAuthorRelation(bookId, author.getId());
            }
        }

        resp.sendRedirect(req.getContextPath() + "/admin/book");
    }

    private void updateBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String title = req.getParameter("title");
        String description = req.getParameter("description");
        String publisher = req.getParameter("publisher");

        double price = 0.0;
        if (req.getParameter("price") != null && !req.getParameter("price").isEmpty()) {
            price = Double.parseDouble(req.getParameter("price"));
        }
        int quantity = 0;
        if (req.getParameter("quantity") != null && !req.getParameter("quantity").isEmpty()) {
            quantity = Integer.parseInt(req.getParameter("quantity"));
        }

        String[] authorIds = req.getParameterValues("authorIds");
        List<Author> selectedAuthors = new ArrayList<>();
        if (authorIds != null) {
            for (String authorId : authorIds) {
                Author author = authorService.findById(Long.parseLong(authorId));
                if (author != null) {
                    selectedAuthors.add(author);
                }
            }
        }

        Part coverImagePart = req.getPart("coverImage");
        String coverImage = null;
        if (coverImagePart != null && coverImagePart.getSize() > 0) {
            coverImage = Paths.get(coverImagePart.getSubmittedFileName()).getFileName().toString();
            String uploadPath = getServletContext().getRealPath("/uploads");
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) uploadDir.mkdir();
            coverImagePart.write(uploadPath + File.separator + coverImage);
        }

        Book book = bookService.findById(id);
        book.setTitle(title);
        book.setDescription(description);
        book.setPublisher(publisher);
        book.setPrice(price);
        book.setQuantity(quantity);
        if (coverImage != null) {
            book.setCoverImage(coverImage);
        }
        book.setAuthors(selectedAuthors);

        bookService.update(book);

        Long bookId = Long.parseLong(id);
        bookService.deleteAuthorsByBookId(bookId);
        for (Author author : selectedAuthors) {
            bookService.insertBookAuthorRelation(bookId, author.getId());
        }

        resp.sendRedirect(req.getContextPath() + "/admin/book");
    }


    private void deleteBook(HttpServletRequest req, HttpServletResponse resp) {
        String id = req.getParameter("id");
        try {
            bookService.delete(id);
            resp.sendRedirect(req.getContextPath() + "/admin/book");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
