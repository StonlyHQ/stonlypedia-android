package org.wikipedia.search;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import org.wikipedia.WikipediaApp;
import org.wikipedia.activity.SingleFragmentActivity;
import org.wikipedia.analytics.IntentFunnel;

public class SearchActivity extends SingleFragmentActivity<SearchFragment> {
    static final String INVOKE_SOURCE_EXTRA = "invokeSource";
    static final String QUERY_EXTRA = "query";
    static final IntentFunnel FUNNEL = new IntentFunnel(WikipediaApp.getInstance());

    public static Intent newIntent(@NonNull Context context, int source, @Nullable String query) {

        if (source == SearchInvokeSource.WIDGET.code()) {
            FUNNEL.logSearchWidgetTap();
        }

        return new Intent(context, SearchActivity.class)
                .putExtra(INVOKE_SOURCE_EXTRA, source)
                .putExtra(QUERY_EXTRA, query);
    }

    @Override
    public SearchFragment createFragment() {
        return SearchFragment.newInstance(getIntent().getIntExtra(INVOKE_SOURCE_EXTRA, -1),
                getIntent().getStringExtra(QUERY_EXTRA));
    }
}
