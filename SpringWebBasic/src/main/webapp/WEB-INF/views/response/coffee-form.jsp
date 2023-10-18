<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style>
        label {
            display: block;
            margin-bottom: 20px;
        }
        .wrap {
            width: 800px;
            margin: 100px auto;
            border: 1px dashed #000;
        }
        .wrap h1 {
            margin: 0 auto;
            padding-bottom: 20px;
            width: fit-content;
            border-bottom: 1px solid #000;
        }
        .wrap .menu {
            font-size: 24px;
            width: 80%;
            margin: 20px auto;
        }
        .wrap .menu select {
            width: 250px;
            height: 50px;
            font-size: 28px;
            margin-top: 10px;
        }
        .clearfix::after {
            content: '';
            display: block;
            clear: both;
        }
    </style>

</head>
<body>

<div class="wrap">
        <h1>커피 주문서</h1>

        <div class="menu">
            <form action="/basic/coffee/result" method="post">
                <label>
                    # 주문 목록 <br>
                    <select id="menu-sel" name="menu">
                        <option value="americano">아메리카노</option>
                        <option value="cafeLatte">카페라떼</option>
                        <option value="macchiato">카라멜 마끼아또</option>                        
                    </select>
                </label>
                <label class="price"># 가격: <span class="price-value">3000</span>원</label>

                <!-- 화면에 렌더링은 안되지만 서버로 보낼 수 있음 -->
                <input id="price-tag" type="hidden" name="price">
                

                <label>
                    <button type="submit">주문하기</button>
                </label>
            </form>

            

        </div>
    </div>

	<script>
		const coffeePrice = {
				americano: 3000,
				cafeLatte: 4500,
				macchiato: 5000
		};
		
		// change: input이나 select 태그의 값이 변했을 때
		// 요소 취득은 대부분 const를 사용함/ 요소를 취득하는 변수 앞에는 $를 붙여주는 것이 관례.
		//변수를 보고 아! 이 안엔 요소가 들어있나보다~ 하고 이해할 수 있음
		const $menu = document.getElementById('menu-sel');
		//e 에 이벤트가 전달됨?
//		$menu.onchange = function(e) {
		//arrow function. 익명함수, 매개변수 1개. 엥 매개변수 없을 때 경우 못봄.
		//이벤트 객체를 받아야 하는 이유. 셀렉트에서 선택된 옵션 값을 받아야하거든! e에는 이벤트 객체가 전달됨.
		//이벤트 객체가 뭔데?
			$menu.onchange = e => {
				//커피를 선택하면 가격이 변해야 함!
				
				//console.log('이벤트 타겟: ', e.target.value);
				//e=이벤트객체. target=이벤트가 발생한 요소(셀렉트) value=옵션의 밸류들이 나옴.
				
				//console.log('변경된 커피값: ', coffeePrice[e.target.value]);
				//coffeeP에 키값 주고 밸류 받아오기
			
				const price = coffeePrice[e.target.value];
				document.querySelector('.price-value').textContent = price;
				//document.querySelector('.price-value').innerText = coffeeP[e.target.value];
					
				const $priceTag = document.getElementById('price-tag');
				$priceTag.value = price;
		}
			
		
			
		
	</script>
</body>
</html>