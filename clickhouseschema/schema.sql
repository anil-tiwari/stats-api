
CREATE DATABASE event_data;
CREATE TABLE click_events (
    campaign_id String,
    client_id String,
    user_id String,
    product_id String,
    click_timestamp UInt64
) ENGINE = MergeTree()
ORDER BY (campaign_id, click_timestamp);

CREATE TABLE view_events (
    campaign_id String,
    client_id String,
    user_id String,
    product_id String,
    click_timestamp UInt64
) ENGINE = MergeTree()
ORDER BY (campaign_id, click_timestamp);

