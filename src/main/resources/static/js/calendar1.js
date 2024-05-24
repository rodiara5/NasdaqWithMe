document.addEventListener('DOMContentLoaded', function() {
    var calendarEl = document.getElementById('calendar');

    var calendar = new FullCalendar.Calendar(calendarEl, {
        initialView: 'dayGridMonth',
        events: '/json/events.json', // JSON 파일의 경로를 지정합니다.
        locale: 'ko', // 한국어 locale 사용
        eventTimeFormat: { // 이벤트 시간 형식 설정
            hour: 'numeric',
            minute: '2-digit',
            hour12: false, // AM/PM 형식으로 시간 표시
        },
        fixedWeekCount: false,
        dayMaxEventRows: true, // 하루에 최대 표시할 일정 행 수
        views: {
            dayGrid: {
                dayMaxEventRows: 2 // 월별 캘린더에서도 일정 행 수 제한
            }
        },
        eventMouseEnter: function(info) {
            var eventObj = info.event;

            // 이전에 생성된 모든 툴팁 제거
            removeTooltips();

            // 툴팁을 만들어서 위치와 내용을 설정합니다.
            var tooltip = document.createElement('div');
            tooltip.classList.add('event-tooltip');
            tooltip.innerHTML = `
                <p>${eventObj.start.toLocaleString()}</p>
                <p>${eventObj.title}</p>
                <p>${eventObj.extendedProps.description}</p>
            `;

            // 툴팁 위치 설정
            var tooltipX = info.jsEvent.pageX - 10; // 마우스 X 위치
            var tooltipY = info.jsEvent.pageY - 10; // 마우스 Y 위치
            tooltip.style.top = tooltipY + 'px';
            tooltip.style.left = tooltipX + 'px';

            // 툴팁을 body에 추가합니다.
            document.body.appendChild(tooltip);

            // 이벤트 요소에 데이터를 연결하여 나중에 제거할 수 있도록 합니다.
            info.event._tooltip = tooltip;
        },
        eventMouseLeave: function(info) {
            // 마우스가 이벤트 요소를 벗어났을 때 툴팁을 제거합니다.
            removeTooltips();
        }
    });

    calendar.render();

    // 모든 툴팁을 제거하는 함수
    function removeTooltips() {
        var tooltips = document.querySelectorAll('.event-tooltip');
        tooltips.forEach(function(tooltip) {
            tooltip.parentNode.removeChild(tooltip);
        });
    }
    
});
