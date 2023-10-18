<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ include file="include/header.jsp" %>
    
    <section>
        <div class="container-fluid">
            <div class="row">
                <div class="col-xs-12 slide-list">
                    <div id="myCarousel" class="carousel slide" data-ride="carousel">
                        <!-- Indicators -->
                        <ol class="carousel-indicators">
                            <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
                            <li data-target="#myCarousel" data-slide-to="1"></li>
                            <li data-target="#myCarousel" data-slide-to="2"></li>
                        </ol>

                        <!-- Wrapper for slides -->
                        <div class="carousel-inner">
                            <div class="item active">
                                <a href="###">
                                <img src="img/slide1.jpg" alt="슬라이드1">
                                </a>
                            </div>
                            <div class="item">
                                <a href="##">
                                <img src="img/slide2.jpg" alt="슬라이드2">
                                </a>
                            </div>
                            <div class="item">
                                <a href="##">
                                <img src="img/slide3.jpg" alt="슬라이드3">
                                </a>
                            </div>
                        </div>

                        <a class="left carousel-control" href="#myCarousel" data-slide="prev">
                            <span class="glyphicon glyphicon-chevron-left"></span>
                            <span class="sr-only">Previous</span>
                        </a>
                        <a class="right carousel-control" href="#myCarousel" data-slide="next">
                            <span class="glyphicon glyphicon-chevron-right"></span>
                            <span class="sr-only">Next</span>
                        </a>
                    </div>
                </div>
            </div>
        </div>
        <!--슬라이드 끝-->
        
    </section>

    
    <!--본문 -->
    <section>

        <div class="container">
            <div class="row">
                <div class="col-xs-12">
                    <h2>상품 그리드 레이아웃 <small>(쿠팡참조)</small></h2>
                </div>
            </div>
            <div class="row">
                <!--xs사이즈에서는 12칸을 다잡는다. (1개씩보임)-->
                <ul class="col-xs-12 prod-list">
                    <!--sm사이즈에서는 6칸을 잡는다 (2개씩보임)-->
                    <li class="col-sm-6">
                        <a href="##" class="prod-link">
                            <img src="img/box1.jpg">
                            <span class="arrow"><i class="glyphicon glyphicon-menu-right"></i></span>
                            <span class="arrow hover">상세보기<i class="glyphicon glyphicon-menu-right"></i></span>
                        </a>
                    </li>
                    <li class="col-sm-6">
                        <a href="##" class="prod-link">
                            <img src="img/box2.jpg">
                            <span class="arrow"><i class="glyphicon glyphicon-menu-right"></i></span>
                            <span class="arrow hover">상세보기<i class="glyphicon glyphicon-menu-right"></i></span>
                        </a>
                    </li>

                    <li class="col-md-3 col-sm-6">
                        <a href="##" class="prod-link">
                            <img src="img/box3.jpg">
                            <span class="arrow"><i class="glyphicon glyphicon-menu-right"></i></span>
                            <span class="arrow hover">상세보기<i class="glyphicon glyphicon-menu-right"></i></span>
                        </a>
                    </li>
                    <li class="col-md-3 col-sm-6">
                        <a href="##" class="prod-link">
                            <img src="img/box4.jpg">
                            <span class="arrow"><i class="glyphicon glyphicon-menu-right"></i></span>
                            <span class="arrow hover">상세보기<i class="glyphicon glyphicon-menu-right"></i></span>
                        </a>
                    </li>
                    <li class="col-md-3 col-sm-6">
                        <a href="##" class="prod-link">
                            <img src="img/box5.jpg">
                            <span class="arrow"><i class="glyphicon glyphicon-menu-right"></i></span>
                            <span class="arrow hover">상세보기<i class="glyphicon glyphicon-menu-right"></i></span>
                        </a>
                    </li>
                    <li class="col-md-3 col-sm-6">
                        <a href="##" class="prod-link">
                            <img src="img/box6.jpg">
                            <span class="arrow"><i class="glyphicon glyphicon-menu-right"></i></span>
                            <span class="arrow hover">상세보기<i class="glyphicon glyphicon-menu-right"></i></span>
                        </a>
                    </li>

                    <li class="col-md-6 col-sm-12">
                        <a href="##" class="prod-link">
                            <img src="img/box7.jpg">
                            <span class="arrow"><i class="glyphicon glyphicon-menu-right"></i></span>
                            <span class="arrow hover">상세보기<i class="glyphicon glyphicon-menu-right"></i></span>
                        </a>
                    </li>
                    <!--md사이즈에서 (4개씩), sm사이즈에서 (2개씩) -->
                    <li class="col-md-3 col-sm-6">
                        <a href="##" class="prod-link">
                            <img src="img/box8.jpg">
                            <span class="arrow"><i class="glyphicon glyphicon-menu-right"></i></span>
                            <span class="arrow hover">상세보기<i class="glyphicon glyphicon-menu-right"></i></span>
                        </a>
                    </li>
                    <li class="col-md-3 col-sm-6">
                        <a href="##" class="prod-link">
                            <img src="img/box9.jpg">
                            <span class="arrow"><i class="glyphicon glyphicon-menu-right"></i></span>
                            <span class="arrow hover">상세보기<i class="glyphicon glyphicon-menu-right"></i></span>
                        </a>
                    </li>
                </ul>
            </div>
        </div>
    </section>
    
    <%@ include file="include/footer.jsp" %>