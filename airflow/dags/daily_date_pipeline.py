from datetime import datetime, timedelta
# from airflow.providers.amazon.aws.hooks.s3 import S3Hook
from airflow import DAG
from airflow.operators.python import PythonOperator

from nas100_news_summary_metrics import execute, companies
from all_industries_finance_metrics import fetch_nasdaq_list, get_valid_nasdaq, save_nasdaq_data, save_industry_data
from nas100_daily_stockprice import insert_data 

def create_dag():
    default_args = {
        'owner': 'airflow',
        'depends_on_past': False,
        'email_on_failure': False,
        'email_on_retry': False,
        'retries': 1,
        'retry_delay': timedelta(seconds=30),
    }

    dag = DAG(
        'daily_sp_data_pipeline',
        default_args=default_args,
        description='S3와 MySQL에 저장하는 파이프라인',
        schedule_interval='0 16 * * MON-FRI',
        start_date=datetime(2024,6,7),
        catchup=False,
    )

    # Ticker 목록 정의
    tickers = list(companies.keys())

    task_execute = PythonOperator(
        task_id='news_execute',
        python_callable=execute,
        op_args=[tickers],
        dag=dag,
    )
        
    task_stockprice = PythonOperator(
        task_id='insert_data',
        python_callable=insert_data,
        op_args=[tickers],
        dag=dag,
    )

        
    fetch_nasdaq_task = PythonOperator(
        task_id='fetch_nasdaq_list',
        python_callable=fetch_nasdaq_list,
        dag=dag,
    )

    get_valid_nasdaq_task = PythonOperator(
        task_id='get_valid_nasdaq',
        python_callable=get_valid_nasdaq,
        provide_context=True,
        dag=dag,
    )

    save_nasdaq_data_operator = PythonOperator(
        task_id='save_nasdaq_data',
        python_callable=save_nasdaq_data,
        provide_context=True,
        dag=dag,
    )

    save_industry_data_operator = PythonOperator(
        task_id='save_industry_data',
        python_callable=save_industry_data,
        provide_context=True,
        dag=dag,
    )

    task_execute >> task_stockprice >> fetch_nasdaq_task >> get_valid_nasdaq_task >> [save_nasdaq_data_operator, save_industry_data_operator]

    return dag

dag = create_dag()


