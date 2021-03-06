<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- Latest Product Section Begin -->
<c:if test="${allPros.size()>0 }">
	<section class="latest-product spad">
		<div class="container">
			<div class="row">
				<div class="col-lg-4 col-md-6">
					<div class="latest-product__text">
						<h4>Latest Products</h4>
						<div class="latest-product__slider owl-carousel">
							<c:forEach var="item" items="${latestPros }" varStatus="loop">
								<c:if test="${(loop.index)%3==0 }">
									<div class="latest-product__slider__item">
								</c:if>
								<a href="/product/proDetail?proId=${item.id }"
									class="latest-product__item">
									<div class="latest-product__item__pic">
										<img src="${item.images.get(0).image }" alt="">
									</div>
									<div class="latest-product__item__text">
										<h6>${item.name }</h6>
										<c:if test="${item.discount>0 }">
											<span class="outer"> <span class="inner">$${item.price
													}</span>
											</span>
											<span>$${item.getDiscountPrice() }</span>
										</c:if>
										<c:if test="${item.discount==0 || item.discount==null}">
											<span>$${item.price }</span>
										</c:if>
									</div>
								</a>
								<c:if
									test="${(loop.index+1)%3==0 ||loop.index+1==latestPros.size()}">
						</div>
</c:if>
</c:forEach>
</div>
</div>
</div>
<div class="col-lg-4 col-md-6">
	<div class="latest-product__text">
		<h4>Top Rated Products</h4>
		<div class="latest-product__slider owl-carousel">
			<c:forEach var="item" items="${ratedPros }" varStatus="loop">
				<c:if test="${(loop.index)%3==0 }">
					<div class="latest-product__slider__item">
				</c:if>
				<a href="/product/proDetail?proId=${item.id }"
					class="latest-product__item">
					<div class="latest-product__item__pic">
						<img src="${item.images.get(0).image }" alt="">
					</div>
					<div class="latest-product__item__text">
						<h6>${item.name }</h6>
						<c:if test="${item.discount>0 }">
							<span class="outer"> <span class="inner">$${item.price
									}</span>
							</span>
							<span>$${item.getDiscountPrice() }</span>
						</c:if>
						<c:if test="${item.discount==0 || item.discount==null}">
							<span>$${item.price }</span>
						</c:if>
					</div>
				</a>
				<c:if test="${(loop.index+1)%3==0 ||loop.index+1==ratedPros.size()}">
		</div>
		</c:if>
		</c:forEach>
	</div>
</div>
</div>
<div class="col-lg-4 col-md-6">
	<div class="latest-product__text">
		<h4>Bestseller</h4>
		<div class="latest-product__slider owl-carousel">
			<c:forEach var="item" items="${bestSeller }" varStatus="loop">
				<c:if test="${(loop.index)%3==0 }">
					<div class="latest-product__slider__item">
				</c:if>
				<a href="/product/proDetail?proId=${item.id }"
					class="latest-product__item">
					<div class="latest-product__item__pic">
						<img src="${item.images.get(0).image }" alt="">
					</div>
					<div class="latest-product__item__text">
						<h6>${item.name }</h6>
						<c:if test="${item.discount>0 }">
							<span class="outer"> <span class="inner">$${item.price
									}</span>
							</span>
							<span>$${item.getDiscountPrice() }</span>
						</c:if>
						<c:if test="${item.discount==0 || item.discount==null}">
							<span>$${item.price }</span>
						</c:if>
					</div>
				</a>
				<c:if
					test="${(loop.index+1)%3==0||loop.index+1==bestSeller.size() }">
		</div>
		</c:if>
		</c:forEach>
	</div>
</div>
</div>
</div>
</div>
</section>
<!-- Latest Product Section End -->

<section class="featured spad">
	<div class="container">
		<div class="row">
			<div class="col-lg-12">
				<div class="section-title">
					<h2>${latestPros.get(0).cat.name }</h2>
				</div>
				<div class="featured__controls">
					<ul>
						<li class="active" data-filter="*">All</li>
					</ul>
				</div>
			</div>
		</div>
		<div class="row featured__filter">
			<c:forEach var="item" items="${allPros }">
				<div class="col-lg-3 col-md-4 col-sm-6 mix oranges fresh-meat">
					<div class="featured__item">
						<div class="featured__item__pic set-bg"
							data-setbg="">
							<a href="/product/proDetail?proId=${item.id }"><img src="${item.images.get(0).image }"></a>
							<ul class="featured__item__pic__hover">
								<li><a href="/auth/like?proId=${item.id }"><i
										class="fa fa-heart"></i></a></li>
								<li>
									<form action="/auth/addToCart" method="post">
										<input name="quantity" value="1" type="hidden"> <input
											name="proId" value="${item.id }" type="hidden"> <a><i
											class="addToCart fa fa-shopping-cart"></i></a>
									</form>
								</li>
							</ul>
						</div>
						<div class="featured__item__text">
							<h6>
								<a href="/product/proDetail?proId=${item.id }">${item.name }</a>
							</h6>
							<c:if test="${item.discount>0 }">
								<h5 class="outer">
									<span class="inner">$${item.price }</span>
								</h5>
								<h5>$${item.getDiscountPrice() }</h5>
							</c:if>
							<c:if test="${item.discount==0 || item.discount==null}">
								<h5>$${item.price }</h5>
							</c:if>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
</section>
</c:if>
<c:if test="${allPros.size()==0 }">
	<section class="featured spad">
		<div class="container">
			<div class="row">
				<h1 style="text-align: center">There's no available Product!</h1>
			</div>
		</div>
	</section>
</c:if>
<!-- Featured Section End -->