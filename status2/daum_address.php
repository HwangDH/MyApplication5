<?php
header("Content-Type: text/html; charset=UTF-8");
?>
<!--autoload=false �Ķ���͸� �̿��Ͽ� �ڵ����� �ε��Ǵ� ���� �����ϴ�.-->
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
    new daum.Postcode({
        oncomplete: function(data) {
            // �˾����� �˻���� �׸��� Ŭ�������� ������ �ڵ带 �ۼ��ϴ� �κ��Դϴ�.
            // ������ �����Ͽ� �پ��� Ȱ����� Ȯ���� ������.
        }
    }).open();
</script>	