import java.util.*;

// 影院管理系统类
public class Main {
    public static List<Movie> movies = new ArrayList<>();
    public static List<User> users = new ArrayList<>();
    public static List<Theater> theaters = new ArrayList<>();
    public static List<Ticket> tickets = new ArrayList<>();
    public static List<Schedule> schedules = new ArrayList<>();
    private static User currentUser;

    public static void main(String[] args) {
        // 初始化影片、用户和放映厅信息
        Data();

        // 运行影院管理系统
        Run();

    }

    // 初始化影片、放映厅和电子票信息
    private static void Data() {
        // 添加电影信息
        Movie movie1 = new Movie("流浪地球", "郭帆", "吴京", "故事发生在不久的将来，" +
                "太阳即将毁灭，" + "人类面临着前所未有的危机，为了能让部分人能继续生存下去，全人类团结一致，" +
                "合力推出了一个名为“流浪地球”的计划。", 125);
        Movie movie2 = new Movie("战狼2", "吴京", "吴京", "根据真实事件改编，" +
                "讲述了退役特种兵冷锋" + "因妹妹冷艳遭非洲武装恐怖分子绑架，遂踏上拯救之路的故事。"
                , 123);
        movies.add(movie1);
        movies.add(movie2);

        // 添加放映厅信息
        Theater theater1 = new Theater(1, 7, 12);
        Theater theater2 = new Theater(2, 7, 12);
        Theater theater3 = new Theater(3, 7, 12);
        Theater theater4 = new Theater(4, 7, 12);
        Theater theater5 = new Theater(5, 7, 12);
        theaters.add(theater1);
        theaters.add(theater2);
        theaters.add(theater3);
        theaters.add(theater4);
        theaters.add(theater5);

        //添加场次信息
        Schedule schedule1 = new Schedule(movie1,theater4,"8:00-10:30",80);
        Schedule schedule2 = new Schedule(movie2,theater3,"9:00-11:30",100);
        schedules.add(schedule1);
        schedules.add(schedule2);
    }

    // 运行影院管理系统
    private static void Run() {

        // 添加用户信息
        User user1 = new User("001", "张三", "123456", 3,
                "2023-01-01", 2000.00, 5, "13812345678",
                "zhangsan@example.com");
        User user2 = new User("002", "李四", "abcdef", 2,
                "2023-02-01", 1500.00, 3, "13987654321",
                "lisi@example.com");
        users.add(user1);
        users.add(user2);

        //添加管理员、经理和前台
        Admin admin = new Admin("admin","ynuinfo#777");
        Manager manager = new Manager("manager","ynuinfo#888");
        FrontDesk frontDesk = new FrontDesk("frontDesk","ynuinfo#999");

        //开始
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        while (!exit) {
            System.out.println("*** 影院管理系统 ***");
            System.out.println("1. 管理员登录");
            System.out.println("2. 经理登录");
            System.out.println("3. 前台登录");
            System.out.println("4. 用户登录");
            System.out.println("5. 退出");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    admin.login();
                    break;
                case 2:
                    manager.login();
                    break;
                case 3:
                    frontDesk.login();
                    break;
                case 4:
                    int flag = 0;
                    User trueUser = null;
                    System.out.print("请输入用户账户名：");
                    String usernameInput = scanner.nextLine();
                    System.out.print("请输入用户密码：");
                    String passwordInput = scanner.nextLine();
                    for (User user : users) {
                        if (user.getUsername().equals(usernameInput) &&
                                user.getPassword().equals(passwordInput)) {
                            flag = 1;
                            trueUser = user;
                        }
                    }
                    if(flag == 1){
                        System.out.println("用户登录成功！");
                        trueUser.showOptions();
                    }else{
                        System.out.println("用户登录失败，用户名或密码错误。");
                    }
                    break;
                case 5:
                    exit = true;
                    System.out.println("已退出");
                    break;
                default:
                    System.out.println("无效的选项，请重新输入！");
            }
        }
        scanner.close();
    }
}

//抽象账号类
abstract class Account{
    public abstract void login();
    public abstract void showOptions();
}

// 影片类
class Movie {
    public String title;
    private String director;
    private String actor;
    private String synopsis;
    private int duration;

    // 构造函数
    public Movie(String title, String director, String actor, String synopsis, int duration) {
        this.title = title;
        this.director = director;
        this.actor = actor;
        this.synopsis = synopsis;
        this.duration = duration;
    }
    public Movie(String title){
        this.title = title;
    }

    // getter和setter方法
    public String getTitle(){
        return title;
    }
    public String getDirector(){
        return director;
    }
    public String getActor(){
        return actor;
    }
    public String getSynopsis(){
        return synopsis;
    }
    public int getDuration(){
        return duration;
    }
    public void setTitle(String T){
        title = T;
    }
    public void setDirector(String Di){
        director = Di;
    }
    public void setActor(String A){
        actor = A;
    }
    public void setSynopsis(String S){
        synopsis = S;
    }
    public void setDuration(int Du){
        duration = Du;
    }
    // ...

    @Override
    public String toString() {
        return "片名：" + title + "\n导演：" + director + "\n主演：" + actor +
                "\n剧情简介：" + synopsis + "\n时长：" + duration + "分钟";
    }
}

// 放映厅类
class Theater {
    public int id;
    public int rows;
    public int columns;
    public boolean[][] seatAvailability;

    public Theater(int id, int rows, int columns) {
        this.id = id;
        this.rows = rows;
        this.columns = columns;
        this.seatAvailability = new boolean[rows][columns];
        // 初始化座位状态，默认都是空闲的
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                seatAvailability[i][j] = true;
            }
        }
    }
    public Theater(int id){
        this.id = id;
    }

    // getter和setter方法
    public int getId(){
        return id;
    }
    public int getRows(){
        return rows;
    }
    public int getColumns(){
        return columns;
    }
    public boolean[][] getSeatAvailability(){
        return seatAvailability;
    }
    public void setId(int I){
        id = I;
    }
    public void setRows(int R){
        rows = R;
    }
    public void setColumns(int C){
        columns =C;
    }

    // 返回指定场次的座位信息
    public String getSeatsInfo() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                sb.append(seatAvailability[i][j] ? "√" : "X").append("\t");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    // 锁定座位
    public boolean lockSeat(int row, int seat) {
        // 检查座位是否可用
        if (row >= 1 && row <= rows && seat >= 1 && seat <= columns && seatAvailability[row - 1][seat - 1]) {
            seatAvailability[row - 1][seat - 1] = false;
            return true;
        } else {
            return false;
        }
    }
}

//场次类
class Schedule {
    public Movie movie;
    public Theater theater;
    public String time;
    private double price;

    public Schedule(Movie movie, Theater theater, String time, double price){
        this.movie = movie;
        this.theater = theater;
        this.time = time;
        this.price = price;
    }
    public Schedule(Movie movie, Theater theater, String time){
        this.movie = movie;
        this.theater = theater;
        this.time = time;
    }

    // 构造函数、getter和setter方法
    public Movie getMovie() {
        return movie;
    }
    public Theater getTheater() {
        return theater;
    }
    public String getTime() {
        return time;
    }
    public double getPrice() {
        return price;
    }
    public void setMovie(Movie M){
        movie = M;
    }
    public void setHall(Theater TH){
        theater = TH;
    }
    public void setTime(String TI){
        time = TI;
    }
    public void setPrice(double P){
        price = P;
    }

    @Override
    public String toString() {
        return "场次 [电影：" + movie.getTitle() + ", 电影院："
                + theater.getId() + "号, 时间：" + time + ", 价格：" + price + "]";
    }
}

//电子票类
class Ticket{
    public String ticketId;
    public Movie ticketMovie;
    public Theater ticketTheater;
    public int row;
    public int columns;

    public Ticket(String ticketId, Movie ticketMovie, Theater ticketTheater, int row, int columns){
        this.ticketId = ticketId;
        this.ticketMovie = ticketMovie;
        this.ticketTheater = ticketTheater;
        this.row = row;
        this.columns = columns;
    }

    public String getTicketId(){
        return ticketId;
    }
    public Movie getTicketMovie(){
        return ticketMovie;
    }
    public Theater getTicketTheater(){
        return ticketTheater;
    }
    public int getRow(){
        return row;
    }
    public int getColumns(){
        return columns;
    }
    public void setTicketId(String TI){
        ticketId = TI;
    }
    public void setTicketMovie(Movie TM){
        ticketMovie = TM;
    }
    public void setTicketTheater(Theater TT){
        ticketTheater = TT;
    }
    public void setRow(int R){
        row = R;
    }
    public void setColumns(int C){
        columns = C;
    }

    public String toString() {
        return " 票的电子id：" + ticketId+ ", 电影名字：" + ticketMovie.getTitle() +
                ", 影院：" + ticketTheater.getId() + "号, 第" + row + "行，" + "第:" + columns + "列";
    }
}

//管理员类
class Admin extends Account {
    private String username;
    private String password;
    private List<User> userList = Main.users;

    public Admin(String username, String password) {
        this.username = username;
        this.password = password;
    }

    //登录
    public void login() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入管理员账户名：");
        String usernameInput = scanner.nextLine();
        System.out.print("请输入管理员密码：");
        String passwordInput = scanner.nextLine();
        if (username.equals(usernameInput) && password.equals(passwordInput)) {
            System.out.println("管理员登录成功！");
            showOptions();
        } else {
            System.out.println("管理员登录失败，用户名或密码错误。");
        }
    }

    //菜单
    public void showOptions() {
        boolean loggedIn = true;
        while (loggedIn) {
            System.out.println("-------请选择要执行的操作：-------");
            System.out.println("1. 修改自身密码");
            System.out.println("2. 重置指定用户的密码");
            System.out.println("3. 列出所有用户信息");
            System.out.println("4. 删除用户信息");
            System.out.println("5. 查询用户信息");
            System.out.println("6. 退出登录");

            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    changePassword();
                    break;
                case 2:
                    resetUserPassword();
                    break;
                case 3:
                    listAllUsers();
                    break;
                case 4:
                    removeUser();
                    break;
                case 5:
                    queryUserInfo();
                    break;
                case 6:
                    loggedIn = false;
                    break;
                default:
                    System.out.println("请选择有效的操作。");
                    break;
            }
        }
    }

    //修改自身密码
    public void changePassword() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入当前管理员密码：");
        String currentPassword = scanner.nextLine();
        if (password.equals(currentPassword)) {
            System.out.print("请输入新密码：");
            String newPassword = scanner.nextLine();
            password = newPassword;
            System.out.println("密码修改成功！");
        } else {
            System.out.println("当前密码输入错误，无法修改密码。");
        }
    }

    //重置指定用户的密码
    public void resetUserPassword() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入要重置密码的用户ID或用户名：");
        String userInput = scanner.nextLine();

        boolean found = false;
        for (User user : userList) {
            if (user.getUsername().equals(userInput) || user.getId().equals(userInput)) {
                // 生成随机密码
                StringBuilder newPassword = new StringBuilder();
                String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890!@#$%^&*";
                for (int i = 0; i < 8; i++) {
                    int randomIndex = (int) (Math.random() * characters.length());
                    char randomChar = characters.charAt(randomIndex);
                    newPassword.append(randomChar);
                }

                // 修改用户密码
                user.setPassword(newPassword.toString());
                found = true;
                System.out.println("用户密码重置成功，新密码已发送到用户注册邮箱。");
                break;
            }
        }

        if (!found) {
            System.out.println("未找到指定用户，请检查输入的用户ID或用户名。");
        }
    }

    //列出所有用户信息
    public void listAllUsers() {
        if (userList.isEmpty()) {
            System.out.println("暂无用户信息。");
        } else {
            System.out.println("所有用户信息如下：");
            for (User user : userList) {
                System.out.println("用户ID：" + user.getId());
                System.out.println("用户名：" + user.getUsername());
                System.out.println("用户级别：" + user.getLevel());
                System.out.println("注册时间：" + user.getRegistrationTime());
                System.out.println("累计消费总金额：" + user.getTotalExpense());
                System.out.println("累计消费次数：" + user.getTotalTickets());
                System.out.println("用户手机号：" + user.getPhoneNumber());
                System.out.println("用户邮箱：" + user.getEmail());
                System.out.println();
            }
        }
    }

    //删除用户信息
    public void removeUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入要删除的用户ID或用户名：");
        String userInput = scanner.nextLine();

        boolean found = false;
        for (User user : userList) {
            if (user.getUsername().equals(userInput) || user.getId().equals(userInput)) {
                System.out.println("确定要删除以下用户信息吗？");
                System.out.println("用户ID：" + user.getId());
                System.out.println("用户名：" + user.getUsername());
                System.out.println("用户级别：" + user.getLevel());
                System.out.println("注册时间：" + user.getRegistrationTime());

                System.out.print("确认删除请输入 Y，取消删除请输入其他任意键：");
                String confirm = scanner.nextLine();
                if (confirm.equalsIgnoreCase("Y")) {
                    userList.remove(user);
                    System.out.println("用户信息已成功删除。");
                } else {
                    System.out.println("删除操作已取消。");
                }

                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("未找到指定用户，请检查输入的用户ID或用户名。");
        }
    }

    //查询用户信息
    public void queryUserInfo() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请选择查询方式：");
        System.out.println("1. 根据用户ID查询");
        System.out.println("2. 根据用户名查询");
        System.out.println("3. 查询所有用户信息");

        int queryChoice = scanner.nextInt();

        switch (queryChoice) {
            case 1:
                System.out.print("请输入要查询的用户ID：");
                String userIdInput = scanner.next();
                for (User user : userList) {
                    if (user.getId().equals(userIdInput)) {
                        System.out.println("用户ID：" + user.getId());
                        System.out.println("用户名：" + user.getUsername());
                        System.out.println("用户级别：" + user.getLevel());
                        System.out.println("注册时间：" + user.getRegistrationTime());
                        System.out.println("累计消费总金额：" + user.getTotalExpense());
                        System.out.println("累计消费次数：" + user.getTotalTickets());
                        System.out.println("用户手机号：" + user.getPhoneNumber());
                        System.out.println("用户邮箱：" + user.getEmail());
                        break;
                    }
                }
                break;
            case 2:
                System.out.print("请输入要查询的用户名：");
                String usernameInput = scanner.next();
                for (User user : userList) {
                    if (user.getUsername().equals(usernameInput)) {
                        System.out.println("用户ID：" + user.getId());
                        System.out.println("用户名：" + user.getUsername());
                        System.out.println("用户级别：" + user.getLevel());
                        System.out.println("注册时间：" + user.getRegistrationTime());
                        System.out.println("累计消费总金额：" + user.getTotalExpense());
                        System.out.println("累计消费次数：" + user.getTotalTickets());
                        System.out.println("用户手机号：" + user.getPhoneNumber());
                        System.out.println("用户邮箱：" + user.getEmail());
                        break;
                    }
                }
                break;
            case 3:
                listAllUsers();
                break;
            default:
                System.out.println("请选择有效的查询方式。");
                break;
        }
    }

    //退出登录
    public void logout() {
        System.out.println("管理员退出登录。");
    }

    public void addUser(User user) {
        userList.add(user);
    }
}

//经理类
class Manager extends Account {
    private String username;
    private String password;
    private List<Movie> movieList = Main.movies;
    private List<Schedule> scheduleList = Main.schedules;

    public Manager(String username, String password) {
        this.username = username;
        this.password = password;
    }

    //登录
    public void login() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入经理账户名：");
        String usernameInput = scanner.nextLine();
        System.out.print("请输入经理密码：");
        String passwordInput = scanner.nextLine();
        if (username.equals(usernameInput) && password.equals(passwordInput)) {
            System.out.println("经理登录成功！");
            showOptions();
        } else {
            System.out.println("经理登录失败，用户名或密码错误。");
        }
    }

    //菜单
    public void showOptions() {
        boolean loggedIn = true;
        while (loggedIn) {
            System.out.println("-------请选择要执行的操作：-------");
            System.out.println("1. 列出所有正在上映的影片消息");
            System.out.println("2. 添加影片的信息");
            System.out.println("3. 修改电影的信息");
            System.out.println("4. 删除影片的信息");
            System.out.println("5. 查询影片的信息");
            System.out.println("6. 增加场次");
            System.out.println("7. 修改场次");
            System.out.println("8. 删除场次");
            System.out.println("9. 列出所有场次信息");
            System.out.println("10. 退出");

            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    listAllMovies();
                    break;
                case 2:
                    addMovieWithScanner();
                    break;
                case 3:
                    updateMovieWithScanner();
                    break;
                case 4:
                    deleteMovieWithScanner();
                    break;
                case 5:
                    searchMovieWithScanner();
                    break;
                case 6:
                    addScheduleWithScanner();
                    break;
                case 7:
                    updateScheduleWithScanner();
                    break;
                case 8:
                    deleteScheduleByScanner();
                    break;
                case 9:
                    listAllSchedules();
                    break;
                case 10:
                    loggedIn = false;
                    System.out.println("谢谢使用，再见！");
                    break;
                default:
                    System.out.println("请选择有效的操作。");
                    break;
            }
        }
    }

    // 影片管理
    // 列出所有正在上映影片的信息
    public void listAllMovies() {
        for (Movie movie : movieList) {
            System.out.println(movie.toString());
            System.out.println();
        }
    }

    // 添加影片的信息
    public void addMovie(Movie movie) {
        movieList.add(movie);
    }
    public void addMovieWithScanner() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("请输入电影标题：");
        String title = scanner.nextLine();

        System.out.print("请输入导演：");
        String director = scanner.nextLine();

        System.out.print("请输入演员：");
        String actor = scanner.nextLine();

        System.out.print("请输入剧情简介：");
        String synopsis = scanner.nextLine();

        System.out.print("请输入时长（分钟）：");
        int duration = scanner.nextInt();
        scanner.nextLine();

        Movie movie = new Movie(title, director, actor, synopsis, duration);
        addMovie(movie);

        System.out.println("电影添加成功！");
    }

    // 修改电影的信息
    public void updateMovie(Movie movieToUpdate, Movie updatedMovie) {
        movieToUpdate.setTitle(updatedMovie.getTitle());
        movieToUpdate.setDirector(updatedMovie.getDirector());
        movieToUpdate.setActor(updatedMovie.getActor());
        movieToUpdate.setSynopsis(updatedMovie.getSynopsis());
        movieToUpdate.setDuration(updatedMovie.getDuration());
    }
    public void updateMovieWithScanner() {
        Scanner scanner = new Scanner(System.in);
        Movie movieToUpdate = null;

        System.out.println("请输入要更新的电影信息：");

        System.out.print("请输入原始电影标题：");
        String originalTitle = scanner.nextLine();

        // 在这里根据原始电影标题找到对应的电影对象，存储在movieToUpdate中
        for (Movie movie : movieList) {
            if (movie.getTitle().equals(originalTitle)) {
                movieToUpdate = movie;
                break;
            }
        }

        if (movieToUpdate == null) {
            System.out.println("未找到与原始电影标题匹配的电影！");
            return;
        }

        System.out.print("请输入更新后的电影标题：");
        String updatedTitle = scanner.nextLine();

        System.out.print("请输入更新后的导演：");
        String updatedDirector = scanner.nextLine();

        System.out.print("请输入更新后的演员：");
        String updatedActor = scanner.nextLine();

        System.out.print("请输入更新后的剧情简介：");
        String updatedSynopsis = scanner.nextLine();

        System.out.print("请输入更新后的时长（分钟）：");
        int updatedDuration = scanner.nextInt();
        scanner.nextLine();

        Movie updatedMovie = new Movie(updatedTitle, updatedDirector,
                updatedActor, updatedSynopsis, updatedDuration);
        updateMovie(movieToUpdate, updatedMovie);

        System.out.println("电影更新成功！");
    }

    // 删除影片的信息
    public void deleteMovie(Movie movie) {
        if (confirmDelete()) {
            movieList.remove(movie);
        } else {
            System.out.println("取消删除操作。");
        }
    }
    public void deleteMovieWithScanner() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("请输入要删除的电影标题：");
        String titleToDelete = scanner.nextLine();

        Movie movieToDelete = null;

        // 遍历电影列表查找要删除的电影对象
        for (Movie movie : movieList) {
            if (movie.getTitle().equals(titleToDelete)) {
                movieToDelete = movie;
                break;
            }
        }

        if (movieToDelete == null) {
            System.out.println("未找到与要删除的电影标题匹配的电影！");
            return;
        }

        deleteMovie(movieToDelete);

        System.out.println("电影删除成功！");
    }

    // 查询影片的信息
    public void searchMovie(String keyword) {
        for (Movie movie : movieList) {
            if (movie.getTitle().equals(keyword) || movie.getDirector().equals(keyword)
                    || movie.getActor().equals(keyword)) {
                System.out.println(movie.toString());
            }
        }
    }
    public void searchMovieWithScanner() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("请输入要搜索的关键词：");
        String keyword = scanner.nextLine();

        searchMovie(keyword);
    }

    // 排片管理
    // 增加场次
    public void addSchedule(Schedule schedule) {
        scheduleList.add(schedule);
    }
    public void addScheduleWithScanner() {
        Scanner scanner = new Scanner(System.in);
        Movie movie1 = null;
        System.out.println("请输入电影标题：");
        String movieTitle = scanner.nextLine();
        for (Movie movie : movieList) {
            if (movie.getTitle().equals(movieTitle)) {
                movie1 = movie;
            }
        }

        System.out.println("请输入影院ID：");
        int theaterId = scanner.nextInt();
        scanner.nextLine();

        System.out.println("请输入日程时间：");
        String scheduleTime = scanner.nextLine();

        System.out.println("请输入票价：");
        double schedulePrice = scanner.nextDouble();
        scanner.nextLine();

        Theater theater = new Theater(theaterId);

        Schedule newSchedule = new Schedule(movie1, theater, scheduleTime, schedulePrice);

        addSchedule(newSchedule);

        System.out.println("日程添加成功！");
    }

    // 修改场次
    public void updateSchedule(Schedule oldSchedule, Schedule newSchedule) {
        scheduleList.remove(oldSchedule);
        scheduleList.add(newSchedule);
    }
    private Schedule getOriginalSchedule(String movieName) {
        // 遍历电影列表，查找与输入电影名称匹配的电影
        for (Movie movie : movieList) {
            if (movie.getTitle().equalsIgnoreCase(movieName)) {
                // 根据电影名字查找放映信息
                Schedule schedule = findScheduleById(movieName);
                if (schedule != null) {
                    return schedule;
                } else {
                    System.out.println("未找到与输入电影名称匹配的放映信息！");
                    return null;
                }
            }
        }
        return null;
    }
    private Schedule findScheduleById(String movieTitle) {
        // 遍历放映列表，查找与输入电影ID匹配的放映信息
        for (Schedule schedule : scheduleList) {
            if (schedule.getMovie().getTitle() == movieTitle) {
                return schedule;
            }
        }
        System.out.println("未找到与输入电影名字匹配的放映信息！");
        return null;
    }
    public void updateScheduleWithScanner() {
        Scanner scanner = new Scanner(System.in);
        Movie movieB = null;

        // 获取原始放映安排信息
        System.out.println("请输入原始电影的名字：");
        String movieName = scanner.nextLine();
        Schedule oldSchedule = getOriginalSchedule(movieName);

        System.out.println("请选择要进行的操作：");
        System.out.println("1. 安排放映电影 B");
        System.out.println("2. 保持空场");

        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice == 1) {
            System.out.println("请输入电影 B 的标题：");
            String movieTitle = scanner.nextLine();
            for (Movie movie : movieList) {
                if (movie.getTitle().equals(movieTitle)) {
                    movieB = movie;
                    break;
                }
            }
            scanner.nextLine();

            System.out.println("请输入新的日程时间：");
            String scheduleTime = scanner.nextLine();

            System.out.println("请输入新的票价：");
            double schedulePrice = scanner.nextDouble();
            scanner.nextLine();

            Theater theater = new Theater(oldSchedule.getTheater().getId());

            Schedule newSchedule = new Schedule(movieB, theater, scheduleTime, schedulePrice);

            updateSchedule(oldSchedule, newSchedule);
        } else if (choice == 2) {
            // 保持空场，不进行安排
        } else {
            System.out.println("无效的选择！");
        }

        System.out.println("放映安排已成功更新！");
    }


    // 删除场次
    public void deleteScheduleByScanner() {
        Scanner scanner = new Scanner(System.in);

        // 读取用户输入的电影名称、影院ID和放映时间等信息
        System.out.println("请输入要删除的电影名称：");
        String movieTitle = scanner.nextLine();
        System.out.println("请输入要删除的影院ID：");
        int theaterId = scanner.nextInt();
        scanner.nextLine();
        System.out.println("请输入要删除的放映时间：");
        String time = scanner.nextLine();

        Iterator<Schedule> iterator = scheduleList.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getMovie().getTitle().equals(movieTitle) && iterator.next()
                    .getTheater().getId() == (theaterId) && iterator.next().getTime().equals(time)) {
                iterator.remove();
                System.out.println("删除成功");
            }
        }System.out.println("删除成功");
    }

    // 列出所有场次信息
    public void listAllSchedules() {
        for (Schedule schedule : scheduleList) {
            System.out.println(schedule.toString());
        }
    }

    // 退出
    public void logout() {
        System.out.println("经理退出登录。");
    }

    // 辅助方法：确认删除操作
    private boolean confirmDelete() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("确认要删除吗？(Y/N): ");
        String confirmation = scanner.nextLine();
        return confirmation.equalsIgnoreCase("Y");
    }
}

//前台类
class FrontDesk extends Account {
    private String username;
    private String password;
    private List<Movie> movieList = Main.movies;
    private List<Schedule> scheduleList = Main.schedules;
    Scanner scanner = new Scanner(System.in);

    public FrontDesk(String username, String password) {
        this.username = username;
        this.password = password;
    }

    //登录
    public void login() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入前台账户名：");
        String usernameInput = scanner.nextLine();
        System.out.print("请输入前台密码：");
        String passwordInput = scanner.nextLine();
        if (username.equals(usernameInput) && password.equals(passwordInput)) {
            System.out.println("前台登录成功！");
            showOptions();
        } else {
            System.out.println("前台登录失败，用户名或密码错误。");
        }
    }

    //菜单
    public void showOptions() {
        boolean loggedIn = true;
        while (loggedIn) {
            System.out.println("-------请选择要执行的操作：-------");
            System.out.println("1. 列出所有正在上映影片的信息");
            System.out.println("2. 列出所有场次的信息");
            System.out.println("3. 列出指定电影和场次的座位信息");
            System.out.println("4. 售票");
            System.out.println("5. 退出");
            System.out.print("请输入选项：");

            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    listAllMovies();
                    break;
                case 2:
                    listAllSchedules();
                    break;
                case 3:
                    listSeatInformation();
                    break;
                case 4:
                    sellTicket();
                    break;
                case 5:
                    loggedIn = false;
                    System.out.println("谢谢使用，再见！");
                    break;
                default:
                    System.out.println("请选择有效的操作。");
                    break;
            }
        }
    }

    //所有影片消息
    public void listAllMovies() {
        for (Movie movie : movieList) {
            System.out.println(movie.toString());
        }
    }

    //所有场次消息
    public void listAllSchedules() {
        for (Schedule schedule : scheduleList) {
            System.out.println(schedule.toString());
        }
    }

    //指定电影场次的座位信息
    private Schedule findScheduleByMovieAndTime(String movieTitle,int theaterId, String scheduleTime) {
        // 遍历放映列表，查找与输入电影片名和场次匹配的放映信息
        for (Schedule schedule : scheduleList) {
            Movie movie = schedule.getMovie();
            if (movie.getTitle().equals(movieTitle) && schedule.getTime().equals(scheduleTime)
                && schedule.getTheater().getId() == theaterId) {
                return schedule;
            }
        }
        return null;
    }
    private void listSeatInformation() {
        System.out.print("请输入电影片名：");
        String movieTitle = scanner.next();
        System.out.print("请输入放映厅id：");
        int thraterId = scanner.nextInt();
        System.out.print("请输入放映时间：");
        String scheduleTime = scanner.next();

        // 根据电影片名和场次查找对应的放映信息
        Schedule schedule = findScheduleByMovieAndTime(movieTitle,thraterId, scheduleTime);

        if (schedule == null) {
            System.out.println("未找到对应的放映信息！");
            return;
        }

        // 获取放映信息中的座位情况
        Theater theater = schedule.getTheater();
        boolean[][] seatAvailability = theater.getSeatAvailability();

        // 统计总座位数和空闲座位数
        int totalSeats = 0;
        int availableSeats = 0;

        for (int row = 0; row < seatAvailability.length; row++) {
            for (int col = 0; col < seatAvailability[row].length; col++) {
                totalSeats++;
                if (seatAvailability[row][col]) {
                    availableSeats++;
                }
            }
        }

        // 打印座位信息
        System.out.println("总座位数：" + totalSeats);
        System.out.println("空闲座位数：" + availableSeats);
        System.out.println("座位信息：");

        for (int row = 0; row < seatAvailability.length; row++) {
            for (int col = 0; col < seatAvailability[row].length; col++) {
                String seatStatus = seatAvailability[row][col] ? "√" : "X";
                System.out.print(seatStatus + "\t");
            }
            System.out.println();
        }
    }

    //售票
    private String getTicketId() {
        return UUID.randomUUID().toString();
    }
    private void sellTicket() {
        System.out.print("请输入电影片名：");
        String movieTitle = scanner.next();
        System.out.print("请输入放映厅id：");
        int thraterId = scanner.nextInt();
        System.out.print("请输入放映时间：");
        String scheduleTime = scanner.next();
        System.out.print("请输入用户名/手机号：");
        String username = scanner.next();

        // 根据电影片名和场次查找对应的放映信息
        Schedule schedule = findScheduleByMovieAndTime(movieTitle,thraterId, scheduleTime);

        if (schedule == null) {
            System.out.println("未找到对应的放映信息！");
            return;
        }

        // 获取放映信息中的座位情况
        Theater theater = schedule.getTheater();
        boolean[][] seatAvailability = theater.getSeatAvailability();

        // 选择座位
        int row, col;
        do {
            System.out.print("请选择座位行号：");
            row = scanner.nextInt();
            System.out.print("请选择座位列号：");
            col = scanner.nextInt();

            if (row < 1 || row > seatAvailability.length || col < 1 || col > seatAvailability[0].length) {
                System.out.println("座位选择无效，请重新选择！");
            } else if (!seatAvailability[row-1][col-1]) {
                System.out.println("该座位已被占用，请重新选择！");
            } else {
                break;
            }
        } while (true);

        // 更新座位情况，并生成电子票ID
        seatAvailability[row-1][col-1] = false;
        String ticketId = getTicketId();
        System.out.println("电影票的电子 ID 编号：" + ticketId);

    }

    //退出
    public void logout() {
        System.out.println("前台退出登录。");
    }
}

// 用户类
class User extends Account {
    private String id;
    private String username;
    private String password;
    private int level;
    private String registrationTime;
    private double totalExpense;
    private int totalTickets;
    private String phoneNumber;
    private String email;
    private boolean isLocked;
    private int loginAttempts;
    private List<Movie> movieList = Main.movies;
    private List<Schedule> scheduleList = Main.schedules;
    private List<Ticket> ticketlist = Main.tickets;
    Scanner scanner = new Scanner(System.in);

    public User(String id, String username, String password, int level,
                String registrationTime, double totalExpense, int totalTickets,
                String phoneNumber, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.level = level;
        this.registrationTime = registrationTime;
        this.totalExpense = totalExpense;
        this.totalTickets = totalTickets;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.isLocked = false;
        this.loginAttempts = 0;
    }

    // getter和setter方法
    public String getId() {
        return id;
    }
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public int getLevel() {
        return level;
    }
    public String getRegistrationTime() {
        return registrationTime;
    }
    public double getTotalExpense() {
        return totalExpense;
    }
    public int getTotalTickets() {
        return totalTickets;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public String getEmail() {
        return email;
    }
    public void setId(String I) {
        id = I;
    }
    public void setUsername(String U) {
        username = U;
    }
    public void setPassword(String PW) {
        password = PW;
    }
    public void setLevel(int L) {
        level = L;
    }
    public void setRegistrationTime(String R) {
        registrationTime = R;
    }
    public void setTotalExpense(double TE) {
        totalExpense = TE;
    }
    public void setTotalTickets(int TT) {
        totalTickets = TT;
    }
    public void setPhoneNumber(String PN) {
        phoneNumber = PN;
    }
    public void setEmail(String E) {
        email = E;
    }

    public boolean isLocked() {
        return isLocked;
    }
    public void setLocked(boolean locked) {
        isLocked = locked;
    }
    public int getLoginAttempts() {
        return loginAttempts;
    }
    public void incrementLoginAttempts() {
        loginAttempts++;
    }
    public void resetLoginAttempts() {
        loginAttempts = 0;
    }

    @Override
    public String toString() {
        return "用户ID：" + id + "\n用户名：" + username + "\n用户级别：" + level + "\n用户注册时间：" +
                registrationTime + "\n用户累计消费总金额：" + totalExpense
                + "\n用户累计消费次数：" + totalTickets + "\n用户手机号：" + phoneNumber + "\n用户邮箱：" + email;
    }

    //登录
    public void login() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入用户账户名：");
        String usernameInput = scanner.nextLine();
        System.out.print("请输入用户密码：");
        String passwordInput = scanner.nextLine();
        if (username.equals(usernameInput) && password.equals(passwordInput)) {
            System.out.println("用户登录成功！");
            showOptions();
        } else {
            System.out.println("用户登录失败，用户名或密码错误。");
        }
    }

    //菜单
    public void showOptions() {
        boolean loggedIn = true;
        while (loggedIn) {
            System.out.println("-------请选择要执行的操作：-------");
            System.out.println("1. 修改自身密码");
            System.out.println("2. 重置密码");
            System.out.println("3. 查看所有电影的信息");
            System.out.println("4. 查看指定电影播放的信息");
            System.out.println("5. 购票支付并取票");
            System.out.println("6. 查看购票历史");
            System.out.println("7. 退出");

            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    changePassword();
                    break;
                case 2:
                    resetPassword();
                    break;
                case 3:
                    listAllMovies();
                    break;
                case 4:
                    listInformation();
                    break;
                case 5:
                    buyTicket();
                    break;
                case 6:
                    ticketHistory();
                    break;
                case 7:
                    loggedIn = false;
                    System.out.println("谢谢使用，再见！");
                    break;
                default:
                    System.out.println("请选择有效的操作。");
                    break;
            }
        }
    }

    //修改密码
    public boolean isValidPassword(String password) {
        // 判断是否密码长度大于8个字符，必须是大小写字母、数字和标点符号的组合
        if (password.length() < 8) {
            return false;
        }
        boolean hasUpperCase = false;
        boolean hasLowerCase = false;
        boolean hasDigit = false;
        boolean hasSpecialChar = false;

        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) {
                hasUpperCase = true;
            } else if (Character.isLowerCase(c)) {
                hasLowerCase = true;
            } else if (Character.isDigit(c)) {
                hasDigit = true;
            } else {
                hasSpecialChar = true;
            }
        }

        return hasUpperCase && hasLowerCase && hasDigit && hasSpecialChar;
    }
    public void changePassword() {
        System.out.print("(密码长度大于8个字符，必须是大小写字母、数字和标点符号的组合)请输入新密码：");
        String newPassword = scanner.nextLine();

        // 调用isValidPassword函数判断密码是否合理
        if (isValidPassword(newPassword)) {
            // 将用户的密码设为新密码
            this.password = newPassword;
            System.out.println("密码修改成功！");
        } else {
            System.out.println("密码不符合要求，请重新输入。");
        }
    }

    //重置密码
    public boolean resetPassword() {
        // 模拟重置密码功能
        System.out.print("请输入用户名：");
        Scanner scanner = new Scanner(System.in);
        String usernameInput = scanner.nextLine();
        System.out.print("请输入注册邮箱地址：");
        String emailInput = scanner.nextLine();

        if (username.equals(usernameInput) && email.equals(emailInput)) {
            // 生成随机密码
            StringBuilder newPassword = new StringBuilder();
            String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890!@#$%^&*";
            for (int i = 0; i < 8; i++) {
                int randomIndex = (int) (Math.random() * characters.length());
                char randomChar = characters.charAt(randomIndex);
                newPassword.append(randomChar);
            }

            // 发送新密码到指定邮箱
            System.out.println("新密码已发送到您的邮箱，请登录邮箱查看。");
            System.out.println("您的新密码是：" + newPassword.toString());
            password = newPassword.toString();
            return true;
        } else {
            System.out.println("用户名和邮箱地址不匹配，重置密码失败。");
            return false;
        }
    }

    // 列出所有正在上映影片的信息
    public void listAllMovies() {
        for (Movie movie : movieList) {
            System.out.println(movie.toString());
        }
    }

    //指定电影的场次信息
    private Schedule findScheduleByMovie(String movieTitle) {
        // 遍历放映列表，查找与输入电影片名和场次匹配的放映信息
        for (Schedule schedule : scheduleList) {
            Movie movie = schedule.getMovie();
            if (movie.getTitle().equals(movieTitle)) {
                return schedule;
            }
        }

        return null;
    }
    private void listInformation() {

        System.out.print("请输入电影片名：");
        String movieTitle = scanner.next();

        // 根据电影片名和场次查找对应的放映信息
        Schedule schedule = findScheduleByMovie(movieTitle);
        int theaterId = schedule.getTheater().getId();
        String scheduleTime = schedule.getTime();

        if (schedule == null) {
            System.out.println("未找到对应的放映信息！");
            return;
        }
        System.out.println("该电影的放映厅是：" + theaterId + "\t" + "时间是：" + scheduleTime);

    }

    //购票支付并取票
    private String generateTicketID() {
        StringBuilder ticketID = new StringBuilder();
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
        for (int i = 0; i < 10; i++) {
            int randomIndex = (int) (Math.random() * characters.length());
            char randomChar = characters.charAt(randomIndex);
            ticketID.append(randomChar);
        }
        return ticketID.toString();
    }
    public boolean buyTicket() {

        //查看信息
        Schedule schedule = null;
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入电影名字：");
        String movieTitle = scanner.nextLine();
        schedule = findScheduleByMovie(movieTitle);
        boolean[][] seatAvailability = schedule.getTheater().getSeatAvailability();
        System.out.println("场次信息如下：");
        for (int row = 0; row < seatAvailability.length; row++) {
            for (int col = 0; col < seatAvailability[row].length; col++) {
                String seatStatus = seatAvailability[row][col] ? "√" : "X";
                System.out.print(seatStatus + "\t");
            }
            System.out.println();
        }

        //购票
        Ticket ticket =new Ticket("1",schedule.getMovie(),schedule.getTheater(),1,1);
        System.out.println("请输入第几行：");
        int theaterRow = scanner.nextInt();
        System.out.println("请输入第几列：");
        int theaterColumn = scanner.nextInt();

        if (seatAvailability[theaterRow - 1][theaterColumn - 1]) {
            System.out.println("座位锁定成功。");
        }else {
            System.out.println("该座位已被占用，请重新选择。");
        }

        // 支付
        double moviePrice = schedule.getPrice();
        double newMoviePrice;
        if (level == 1) {
            newMoviePrice = moviePrice;
            System.out.print("您是铜级用户，价格为：" + newMoviePrice + "请输入密码：");
        } else if (level == 2) {
            newMoviePrice = moviePrice * 0.95;
            System.out.print("您是银级用户，价格为：" + newMoviePrice + "请输入密码：");
        } else if (level == 3) {
            newMoviePrice = moviePrice * 0.88;
            System.out.print("您是金级用户，价格为：" + newMoviePrice + "请输入密码：");
        }
        String checkPassword = scanner.next();

        //购票成功，信息存入
        String ticketId;
        if (checkPassword.equals(password)) {
            ticketId = generateTicketID();
            System.out.println("支付成功！生成电影票的电子ID编号：" + ticketId);
            ticket.ticketId = ticketId;
            ticket.ticketMovie.title = movieTitle;
            ticket.ticketTheater.id = schedule.theater.id;
            ticket.row = theaterRow;
            ticket.columns = theaterColumn;
            ticketlist.add(ticket);
            return true;
        } else {
            System.out.println("支付失败，座位将被释放。");
            seatAvailability[theaterRow - 1][theaterColumn - 1] = true;
            return false;
        }
    }

    //查看购票历史
    public void ticketHistory() {
        for (Ticket ticket : ticketlist) {
            System.out.println(ticket.toString());
        }
    }
}