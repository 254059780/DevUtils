package dev.utils.app;

import android.content.res.ColorStateList;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Build;
import android.text.Html;
import android.text.InputFilter;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.List;

import dev.utils.LogPrintUtils;

/**
 * detail: TextView 工具类
 * @author Ttt
 * <pre>
 *     获取字体信息 Paint.FontMetrics
 *     @see <a href="https://blog.csdn.net/superbigcupid/article/details/47153139"/>
 *     @see <a href="http://www.cnblogs.com/tianzhijiexian/p/4297664.html"/>
 *     <p></p>
 *     TextView 设置行间距、行高, 以及字间距
 *     @see <a href="https://blog.csdn.net/shanshan_1117/article/details/79564271"/>
 *     <p></p>
 *     android:includeFontPadding
 *     @see <a href="https://blog.csdn.net/bdmh/article/details/78110557"/>
 *     <p></p>
 *     设置文字水平间距: {@link TextViewUtils#setLetterSpacing(View, float)}
 *     android:letterSpacing
 *     设置文字行间距 ( 行高 ): {@link TextViewUtils#setLineSpacing(View, float)}、{@link TextViewUtils#setLineSpacingAndMultiplier(View, float, float)}
 *     android:lineSpacingExtra
 *     android:lineSpacingMultiplier
 *     <p></p>
 *     setPaintFlags:
 *     Paint.ANTI_ALIAS_FLAG 抗锯齿标志
 *     Paint.FILTER_BITMAP_FLAG 使位图过滤的位掩码标志
 *     Paint.DITHER_FLAG 使位图进行有利的抖动的位掩码标志
 *     Paint.UNDERLINE_TEXT_FLAG 下划线
 *     Paint.STRIKE_THRU_TEXT_FLAG 中划线
 *     Paint.FAKE_BOLD_TEXT_FLAG 加粗
 *     Paint.LINEAR_TEXT_FLAG 使文本平滑线性扩展的油漆标志
 *     Paint.SUBPIXEL_TEXT_FLAG 使文本的亚像素定位的绘图标志
 *     Paint.EMBEDDED_BITMAP_TEXT_FLAG 绘制文本时允许使用位图字体的绘图标志
 * </pre>
 */
public final class TextViewUtils {

    private TextViewUtils() {
    }

    // 日志 TAG
    private static final String TAG = TextViewUtils.class.getSimpleName();

    // =================
    // = 获取 TextView =
    // =================

    /**
     * 获取 TextView
     * @param view {@link View}
     * @param <T>  泛型
     * @return {@link TextView}
     */
    public static <T extends TextView> T getTextView(final View view) {
        if (view != null) {
            try {
                return (T) view;
            } catch (Exception e) {
                LogPrintUtils.eTag(TAG, e, "getTextView");
            }
        }
        return null;
    }

    // ========
    // = Text =
    // ========

    /**
     * 获取 Hint 文本
     * @param textView {@link TextView}
     * @param <T>      泛型
     * @return {@link TextView#getHint()}
     */
    public static <T extends TextView> String getHint(final T textView) {
        if (textView != null) {
            if (textView.getHint() != null) {
                return textView.getHint().toString();
            }
        }
        return null;
    }

    /**
     * 获取 Hint 文本
     * @param view {@link TextView}
     * @return {@link TextView#getHint()}
     */
    public static String getHint(final View view) {
        return getHint(getTextView(view));
    }

    /**
     * 获取文本
     * @param textView {@link TextView}
     * @param <T>      泛型
     * @return {@link TextView#getText()}
     */
    public static <T extends TextView> String getText(final T textView) {
        if (textView != null) {
            return textView.getText().toString();
        }
        return null;
    }

    /**
     * 获取文本
     * @param view {@link TextView}
     * @return {@link TextView#getText()}
     */
    public static String getText(final View view) {
        return getText(getTextView(view));
    }

    // =

    /**
     * 获取多个 TextView Hint 文本
     * @param views View(TextView)[]
     * @return {@link List<String>} 多个 TextView Hint 文本
     */
    public static List<String> getHints(final View... views) {
        List<String> lists = new ArrayList<>();
        if (views != null) {
            for (View view : views) {
                String text = getHint(view);
                if (text != null) {
                    lists.add(text);
                }
            }
        }
        return lists;
    }

    /**
     * 获取多个 TextView Hint 文本
     * @param views TextView[]
     * @param <T>   泛型
     * @return {@link List<String>} 多个 TextView Hint 文本
     */
    public static <T extends TextView> List<String> getHints(final T... views) {
        List<String> lists = new ArrayList<>();
        if (views != null) {
            for (T view : views) {
                String text = getHint(view);
                if (text != null) {
                    lists.add(text);
                }
            }
        }
        return lists;
    }

    /**
     * 获取多个 TextView 文本
     * @param views View(TextView)[]
     * @return {@link List<String>} 多个 TextView 文本
     */
    public static List<String> getTexts(final View... views) {
        List<String> lists = new ArrayList<>();
        if (views != null) {
            for (View view : views) {
                String text = getText(view);
                if (text != null) {
                    lists.add(text);
                }
            }
        }
        return lists;
    }

    /**
     * 获取多个 TextView 文本
     * @param views TextView[]
     * @param <T>   泛型
     * @return {@link List<String>} 多个 TextView 文本
     */
    public static <T extends TextView> List<String> getTexts(final T... views) {
        List<String> lists = new ArrayList<>();
        if (views != null) {
            for (T view : views) {
                String text = getText(view);
                if (text != null) {
                    lists.add(text);
                }
            }
        }
        return lists;
    }

    // =

    /**
     * 设置 Hint 文本
     * @param textView {@link TextView}
     * @param text     Hint text
     * @param <T>      泛型
     */
    public static <T extends TextView> void setHint(final T textView, final String text) {
        if (textView != null) {
            textView.setHint(text);
        }
    }

    /**
     * 设置 Hint 文本
     * @param view {@link TextView}
     * @param text Hint text
     * @return {@link View}
     */
    public static View setHint(final View view, final String text) {
        setHint(getTextView(view), text);
        return view;
    }

    /**
     * 设置文本
     * @param textView {@link TextView}
     * @param text     TextView text
     * @param <T>      泛型
     */
    public static <T extends TextView> void setText(final T textView, final String text) {
        if (textView != null) {
            textView.setText(text);
        }
    }

    /**
     * 设置文本
     * @param view {@link TextView}
     * @param text TextView text
     * @return {@link View}
     */
    public static View setText(final View view, final String text) {
        setText(getTextView(view), text);
        return view;
    }

    // =

    /**
     * 设置多个 TextView 文本
     * @param text  TextView text
     * @param views View(TextView)[]
     */
    public static void setTexts(final String text, final View... views) {
        if (views != null) {
            for (View view : views) {
                setText(view, text);
            }
        }
    }

    /**
     * 设置多个 TextView 文本
     * @param text  TextView text
     * @param views TextView[]
     * @param <T>   泛型
     */
    public static <T extends TextView> void setTexts(final String text, final T... views) {
        if (views != null) {
            for (T view : views) {
                setText(view, text);
            }
        }
    }

    // =

    /**
     * 设置 Html 内容
     * @param textView {@link TextView}
     * @param content  Html content
     * @param <T>      泛型
     */
    public static <T extends TextView> void setHtmlText(final T textView, final String content) {
        if (textView != null && content != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                textView.setText(Html.fromHtml(content, Html.FROM_HTML_MODE_LEGACY));
            } else {
                textView.setText(Html.fromHtml(content));
            }
        }
    }

    /**
     * 设置 Html 内容
     * @param view    {@link TextView}
     * @param content Html content
     * @return {@link View}
     */
    public static View setHtmlText(final View view, final String content) {
        setHtmlText(getTextView(view), content);
        return view;
    }

    // =

    /**
     * 设置多个 TextView Html 内容
     * @param content Html content
     * @param views   View(TextView)[]
     */
    public static void setHtmlTexts(final String content, final View... views) {
        if (content != null && views != null) {
            for (View view : views) {
                setHtmlText(view, content);
            }
        }
    }

    /**
     * 设置多个 TextView Html 内容
     * @param content Html content
     * @param views   TextView[]
     * @param <T>     泛型
     */
    public static <T extends TextView> void setHtmlTexts(final String content, final T... views) {
        if (content != null && views != null) {
            for (T view : views) {
                setHtmlText(view, content);
            }
        }
    }

    // ============
    // = 属性相关 =
    // ============

    /**
     * 获取 Hint 字体颜色
     * @param textView {@link TextView}
     * @param <T>      泛型
     * @return {@link ColorStateList}
     */
    public static <T extends TextView> ColorStateList getHintTextColors(final T textView) {
        if (textView != null) {
            return textView.getHintTextColors();
        }
        return null;
    }

    /**
     * 获取 Hint 字体颜色
     * @param view {@link TextView}
     * @return {@link ColorStateList}
     */
    public static ColorStateList getHintTextColors(final View view) {
        return getHintTextColors(getTextView(view));
    }

    // =

    /**
     * 设置 Hint 字体颜色
     * @param textView {@link TextView}
     * @param color    R.color.id
     * @param <T>      泛型
     */
    public static <T extends TextView> void setHintTextColor(final T textView, @ColorInt final int color) {
        if (textView != null) {
            textView.setHintTextColor(color);
        }
    }

    /**
     * 设置 Hint 字体颜色
     * @param view  {@link TextView}
     * @param color R.color.id
     * @return {@link View}
     */
    public static View setHintTextColor(final View view, @ColorInt final int color) {
        setHintTextColor(getTextView(view), color);
        return view;
    }

    /**
     * 设置 Hint 字体颜色
     * @param textView {@link TextView}
     * @param colors   {@link ColorStateList}
     * @param <T>      泛型
     */
    public static <T extends TextView> void setHintTextColor(final T textView, final ColorStateList colors) {
        if (textView != null) {
            textView.setHintTextColor(colors);
        }
    }

    /**
     * 设置 Hint 字体颜色
     * @param view   {@link TextView}
     * @param colors {@link ColorStateList}
     * @return {@link View}
     */
    public static View setHintTextColor(final View view, final ColorStateList colors) {
        setHintTextColor(getTextView(view), colors);
        return view;
    }

    // =

    /**
     * 获取字体颜色
     * @param textView {@link TextView}
     * @param <T>      泛型
     * @return {@link ColorStateList}
     */
    public static <T extends TextView> ColorStateList getTextColors(final T textView) {
        if (textView != null) {
            return textView.getTextColors();
        }
        return null;
    }

    /**
     * 获取字体颜色
     * @param view {@link TextView}
     * @return {@link ColorStateList}
     */
    public static ColorStateList getTextColors(final View view) {
        return getTextColors(getTextView(view));
    }

    // =

    /**
     * 设置字体颜色
     * @param textView {@link TextView}
     * @param color    R.color.id
     * @param <T>      泛型
     */
    public static <T extends TextView> void setTextColor(final T textView, @ColorInt final int color) {
        if (textView != null) {
            textView.setTextColor(color);
        }
    }

    /**
     * 设置字体颜色
     * @param view  {@link TextView}
     * @param color R.color.id
     * @return {@link View}
     */
    public static View setTextColor(final View view, @ColorInt final int color) {
        setTextColor(getTextView(view), color);
        return view;
    }

    /**
     * 设置字体颜色
     * @param textView {@link TextView}
     * @param colors   {@link ColorStateList}
     * @param <T>      泛型
     */
    public static <T extends TextView> void setTextColor(final T textView, final ColorStateList colors) {
        if (textView != null) {
            textView.setTextColor(colors);
        }
    }

    /**
     * 设置字体颜色
     * @param view   {@link TextView}
     * @param colors {@link ColorStateList}
     * @return {@link View}
     */
    public static View setTextColor(final View view, final ColorStateList colors) {
        setTextColor(getTextView(view), colors);
        return view;
    }

    // =

    /**
     * 设置多个 TextView Hint 字体颜色
     * @param color R.color.id
     * @param views View(TextView)[]
     */
    public static void setHintTextColors(@ColorInt final int color, final View... views) {
        if (views != null) {
            for (View view : views) {
                setHintTextColor(view, color);
            }
        }
    }

    /**
     * 设置多个 TextView Hint 字体颜色
     * @param color R.color.id
     * @param views TextView[]
     * @param <T>   泛型
     */
    public static <T extends TextView> void setHintTextColors(@ColorInt final int color, final T... views) {
        if (views != null) {
            for (T view : views) {
                setHintTextColor(view, color);
            }
        }
    }

    /**
     * 设置多个 TextView Hint 字体颜色
     * @param colors {@link ColorStateList}
     * @param views  View(TextView)[]
     */
    public static void setHintTextColors(final ColorStateList colors, final View... views) {
        if (views != null) {
            for (View view : views) {
                setHintTextColor(view, colors);
            }
        }
    }

    /**
     * 设置多个 TextView Hint 字体颜色
     * @param colors {@link ColorStateList}
     * @param views  TextView[]
     * @param <T>    泛型
     */
    public static <T extends TextView> void setHintTextColors(final ColorStateList colors, final T... views) {
        if (views != null) {
            for (T view : views) {
                setHintTextColor(view, colors);
            }
        }
    }

    // =

    /**
     * 设置多个 TextView 字体颜色
     * @param color R.color.id
     * @param views View(TextView)[]
     */
    public static void setTextColors(@ColorInt final int color, final View... views) {
        if (views != null) {
            for (View view : views) {
                setTextColor(view, color);
            }
        }
    }

    /**
     * 设置多个 TextView 字体颜色
     * @param color R.color.id
     * @param views TextView[]
     * @param <T>   泛型
     */
    public static <T extends TextView> void setTextColors(@ColorInt final int color, final T... views) {
        if (views != null) {
            for (T view : views) {
                setTextColor(view, color);
            }
        }
    }

    /**
     * 设置多个 TextView 字体颜色
     * @param colors {@link ColorStateList}
     * @param views  View(TextView)[]
     */
    public static void setTextColors(final ColorStateList colors, final View... views) {
        if (views != null) {
            for (View view : views) {
                setTextColor(view, colors);
            }
        }
    }

    /**
     * 设置多个 TextView 字体颜色
     * @param colors {@link ColorStateList}
     * @param views  TextView[]
     * @param <T>    泛型
     */
    public static <T extends TextView> void setTextColors(final ColorStateList colors, final T... views) {
        if (views != null) {
            for (T view : views) {
                setTextColor(view, colors);
            }
        }
    }

    // ============
    // = 字体相关 =
    // ============

    /**
     * 设置字体
     * @param textView {@link TextView}
     * @param typeface {@link Typeface} 字体样式
     * @param <T>      泛型
     */
    public static <T extends TextView> void setTypeface(final T textView, final Typeface typeface) {
        if (textView != null && typeface != null) {
            textView.setTypeface(typeface);
        }
    }

    /**
     * 设置字体
     * @param textView {@link TextView}
     * @param typeface {@link Typeface} 字体样式
     * @param style    样式
     * @param <T>      泛型
     */
    public static <T extends TextView> void setTypeface(final T textView, final Typeface typeface, final int style) {
        if (textView != null && typeface != null) {
            textView.setTypeface(typeface, style);
        }
    }

    /**
     * 设置字体
     * @param view     {@link TextView}
     * @param typeface {@link Typeface} 字体样式
     * @return {@link View}
     */
    public static View setTypeface(final View view, final Typeface typeface) {
        setTypeface(getTextView(view), typeface);
        return view;
    }

    /**
     * 设置字体
     * @param view     {@link TextView}
     * @param typeface {@link Typeface} 字体样式
     * @param style    样式
     * @return {@link View}
     */
    public static View setTypeface(final View view, final Typeface typeface, final int style) {
        setTypeface(getTextView(view), typeface, style);
        return view;
    }

    // =

    /**
     * 设置字体大小 - px 像素
     * @param textView {@link TextView}
     * @param size     字体大小
     * @param <T>      泛型
     */
    public static <T extends TextView> void setTextSizeByPx(final T textView, final float size) {
        setTextSize(textView, TypedValue.COMPLEX_UNIT_PX, size);
    }

    /**
     * 设置字体大小 - sp 缩放像素
     * @param textView {@link TextView}
     * @param size     字体大小
     * @param <T>      泛型
     */
    public static <T extends TextView> void setTextSizeBySp(final T textView, final float size) {
        setTextSize(textView, TypedValue.COMPLEX_UNIT_SP, size);
    }

    /**
     * 设置字体大小 - dp 与设备无关的像素
     * @param textView {@link TextView}
     * @param size     字体大小
     * @param <T>      泛型
     */
    public static <T extends TextView> void setTextSizeByDp(final T textView, final float size) {
        setTextSize(textView, TypedValue.COMPLEX_UNIT_DIP, size);
    }

    /**
     * 设置字体大小 - inches 英寸
     * @param textView {@link TextView}
     * @param size     字体大小
     * @param <T>      泛型
     */
    public static <T extends TextView> void setTextSizeByIn(final T textView, final float size) {
        setTextSize(textView, TypedValue.COMPLEX_UNIT_IN, size);
    }

    // =

    /**
     * 设置字体大小 - px 像素
     * @param view {@link TextView}
     * @param size 字体大小
     * @return {@link View}
     */
    public static View setTextSizeByPx(final View view, final float size) {
        setTextSize(getTextView(view), TypedValue.COMPLEX_UNIT_PX, size);
        return view;
    }

    /**
     * 设置字体大小 - sp 缩放像素
     * @param view {@link TextView}
     * @param size 字体大小
     * @return {@link View}
     */
    public static View setTextSizeBySp(final View view, final float size) {
        setTextSize(getTextView(view), TypedValue.COMPLEX_UNIT_SP, size);
        return view;
    }

    /**
     * 设置字体大小 - dp 与设备无关的像素
     * @param view {@link TextView}
     * @param size 字体大小
     * @return {@link View}
     */
    public static View setTextSizeByDp(final View view, final float size) {
        setTextSize(getTextView(view), TypedValue.COMPLEX_UNIT_DIP, size);
        return view;
    }

    /**
     * 设置字体大小 - inches 英寸
     * @param view {@link TextView}
     * @param size 字体大小
     * @return {@link View}
     */
    public static View setTextSizeByIn(final View view, final float size) {
        setTextSize(getTextView(view), TypedValue.COMPLEX_UNIT_IN, size);
        return view;
    }

    // =

    /**
     * 设置字体大小
     * @param textView {@link TextView}
     * @param unit     字体参数类型
     * @param size     字体大小
     * @param <T>      泛型
     */
    public static <T extends TextView> void setTextSize(final T textView, final int unit, final float size) {
        if (textView != null) {
            textView.setTextSize(unit, size);
        }
    }

    /**
     * 设置字体大小
     * @param view {@link TextView}
     * @param unit 字体参数类型
     * @param size 字体大小
     * @return {@link View}
     */
    public static View setTextSize(final View view, final int unit, final float size) {
        setTextSize(getTextView(view), unit, size);
        return view;
    }

    // =

    /**
     * 设置多个 TextView 字体大小
     * @param views View(TextView)[]
     * @param unit  参数类型
     * @param size  字体大小
     */
    public static void setTextSizes(final View[] views, final int unit, final float size) {
        if (views != null) {
            for (View view : views) {
                setTextSize(view, unit, size);
            }
        }
    }

    /**
     * 设置多个 TextView 字体大小
     * @param views TextView[]
     * @param unit  参数类型
     * @param size  字体大小
     * @param <T>   泛型
     */
    public static <T extends TextView> void setTextSizes(final T[] views, final int unit, final float size) {
        if (views != null) {
            for (T view : views) {
                setTextSize(view, unit, size);
            }
        }
    }

    // =

    /**
     * 获取 TextView 字体大小 - px
     * @param textView {@link TextView}
     * @param <T>      泛型
     * @return 字体大小 (px)
     */
    public static <T extends TextView> float getTextSize(final T textView) {
        if (textView != null) {
            return textView.getTextSize();
        }
        return -1f;
    }

    /**
     * 获取 TextView 字体大小 - px
     * @param view {@link TextView}
     * @return 字体大小 (px)
     */
    public static float getTextSize(final View view) {
        return getTextSize(getTextView(view));
    }

    // =

    /**
     * 清空 flags
     * @param textView {@link TextView}
     * @param <T>      泛型
     */
    public static <T extends TextView> void clearFlags(final T textView) {
        if (textView != null) {
            textView.setPaintFlags(0);
        }
    }

    /**
     * 清空 flags
     * @param view {@link TextView}
     * @return {@link View}
     */
    public static View clearFlags(final View view) {
        clearFlags(getTextView(view));
        return view;
    }

    // =

    /**
     * 设置 TextView 是否加粗
     * @param textView {@link TextView}
     * @param isBold   {@code true} yes, {@code false} no
     * @param <T>      泛型
     */
    public static <T extends TextView> void setBold(final T textView, final boolean isBold) {
        if (textView != null) {
            textView.setTypeface(Typeface.defaultFromStyle(isBold ? Typeface.BOLD : Typeface.NORMAL));
        }
    }

    /**
     * 设置 TextView 是否加粗
     * @param textView {@link TextView}
     * @param typeface {@link Typeface} 字体样式
     * @param isBold   {@code true} yes, {@code false} no
     * @param <T>      泛型
     */
    public static <T extends TextView> void setBold(final T textView, final Typeface typeface, final boolean isBold) {
        if (textView != null && typeface != null) {
            textView.setTypeface(typeface, isBold ? Typeface.BOLD : Typeface.NORMAL);
        }
    }

    /**
     * 设置 TextView 是否加粗
     * @param view   {@link TextView}
     * @param isBold {@code true} yes, {@code false} no
     * @return {@link View}
     */
    public static View setBold(final View view, final boolean isBold) {
        setBold(getTextView(view), isBold);
        return view;
    }

    /**
     * 设置 TextView 是否加粗
     * @param view     {@link TextView}
     * @param typeface {@link Typeface} 字体样式
     * @param isBold   {@code true} yes, {@code false} no
     * @return {@link View}
     */
    public static View setBold(final View view, final Typeface typeface, final boolean isBold) {
        setBold(getTextView(view), typeface, isBold);
        return view;
    }

    // =

    /**
     * 设置下划线
     * @param textView {@link TextView}
     * @param <T>      泛型
     */
    public static <T extends TextView> void setUnderlineText(final T textView) {
        setUnderlineText(textView, true);
    }

    /**
     * 设置下划线并加清晰
     * @param textView    {@link TextView}
     * @param isAntiAlias 是否消除锯齿
     * @param <T>         泛型
     */
    public static <T extends TextView> void setUnderlineText(final T textView, final boolean isAntiAlias) {
        if (textView != null) {
            textView.setPaintFlags(textView.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
            if (isAntiAlias) {
                textView.setPaintFlags(textView.getPaintFlags() | Paint.ANTI_ALIAS_FLAG);
            }
        }
    }

    // =

    /**
     * 设置下划线
     * @param view {@link TextView}
     * @return {@link View}
     */
    public static View setUnderlineText(final View view) {
        setUnderlineText(getTextView(view), true);
        return view;
    }

    /**
     * 设置下划线并加清晰
     * @param view        {@link TextView}
     * @param isAntiAlias 是否消除锯齿
     * @return {@link View}
     */
    public static View setUnderlineText(final View view, final boolean isAntiAlias) {
        setUnderlineText(getTextView(view), isAntiAlias);
        return view;
    }

    // =

    /**
     * 设置中划线
     * @param textView {@link TextView}
     * @param <T>      泛型
     */
    public static <T extends TextView> void setStrikeThruText(final T textView) {
        setStrikeThruText(textView, true);
    }

    /**
     * 设置中划线并加清晰
     * @param textView    {@link TextView}
     * @param isAntiAlias 是否消除锯齿
     * @param <T>         泛型
     */
    public static <T extends TextView> void setStrikeThruText(final T textView, final boolean isAntiAlias) {
        if (textView != null) {
            textView.setPaintFlags(textView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            if (isAntiAlias) {
                textView.setPaintFlags(textView.getPaintFlags() | Paint.ANTI_ALIAS_FLAG);
            }
        }
    }

    // =

    /**
     * 设置中划线
     * @param view {@link TextView}
     * @return {@link View}
     */
    public static View setStrikeThruText(final View view) {
        setStrikeThruText(getTextView(view), true);
        return view;
    }

    /**
     * 设置中划线并加清晰
     * @param view        {@link TextView}
     * @param isAntiAlias 是否消除锯齿
     * @return {@link View}
     */
    public static View setStrikeThruText(final View view, final boolean isAntiAlias) {
        setStrikeThruText(getTextView(view), isAntiAlias);
        return view;
    }

    // =

    /**
     * 设置文字水平间距
     * <pre>
     *     android:letterSpacing
     * </pre>
     * @param textView      {@link TextView}
     * @param letterSpacing 文字水平间距值
     * @param <T>           泛型
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static <T extends TextView> void setLetterSpacing(final T textView, final float letterSpacing) {
        if (textView != null) {
            textView.setLetterSpacing(letterSpacing);
        }
    }

    /**
     * 设置文字水平间距
     * @param view          {@link TextView}
     * @param letterSpacing 文字水平间距值
     * @return {@link View}
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static View setLetterSpacing(final View view, final float letterSpacing) {
        setLetterSpacing(getTextView(view), letterSpacing);
        return view;
    }

    // =

    /**
     * 设置文字行间距 ( 行高 )
     * @param textView    {@link TextView}
     * @param lineSpacing 文字行间距 ( 行高 ), android:lineSpacingExtra
     * @param <T>         泛型
     */
    public static <T extends TextView> void setLineSpacing(final T textView, final float lineSpacing) {
        setLineSpacingAndMultiplier(textView, lineSpacing, 1.0f);
    }

    /**
     * 设置文字行间距 ( 行高 )、行间距倍数
     * @param textView    {@link TextView}
     * @param lineSpacing 文字行间距 ( 行高 ), android:lineSpacingExtra
     * @param multiplier  行间距倍数, android:lineSpacingMultiplier
     * @param <T>         泛型
     */
    public static <T extends TextView> void setLineSpacingAndMultiplier(final T textView, final float lineSpacing, final float multiplier) {
        if (textView != null) {
            textView.setLineSpacing(lineSpacing, multiplier);
        }
    }

    // =

    /**
     * 设置文字行间距 ( 行高 )
     * @param view        {@link TextView}
     * @param lineSpacing 文字行间距 ( 行高 ), android:lineSpacingExtra
     * @return {@link View}
     */
    public static View setLineSpacing(final View view, final float lineSpacing) {
        setLineSpacingAndMultiplier(getTextView(view), lineSpacing, 1.0f);
        return view;
    }

    /**
     * 设置文字行间距 ( 行高 )、行间距倍数
     * @param view        {@link TextView}
     * @param lineSpacing 文字行间距 ( 行高 ), android:lineSpacingExtra
     * @param multiplier  行间距倍数, android:lineSpacingMultiplier
     * @return {@link View}
     */
    public static View setLineSpacingAndMultiplier(final View view, final float lineSpacing, final float multiplier) {
        setLineSpacingAndMultiplier(getTextView(view), lineSpacing, multiplier);
        return view;
    }

    // =

    /**
     * 设置字体水平方向的缩放
     * <pre>
     *     android:textScaleX
     * </pre>
     * @param textView {@link TextView}
     * @param size     缩放比例
     * @param <T>      泛型
     */
    public static <T extends TextView> void setTextScaleX(final T textView, final float size) {
        if (textView != null) {
            textView.setTextScaleX(size);
        }
    }

    /**
     * 设置字体水平方向的缩放
     * @param view {@link TextView}
     * @param size 缩放比例
     * @return {@link View}
     */
    public static View setTextScaleX(final View view, final float size) {
        setTextScaleX(getTextView(view), size);
        return view;
    }

    // =

    /**
     * 设置是否保留字体留白间隙区域
     * <pre>
     *     android:includeFontPadding
     * </pre>
     * @param textView   {@link TextView}
     * @param includepad 是否保留字体留白间隙区域
     * @param <T>        泛型
     */
    public static <T extends TextView> void setIncludeFontPadding(final T textView, final boolean includepad) {
        if (textView != null) {
            textView.setIncludeFontPadding(includepad);
        }
    }

    /**
     * 设置是否保留字体留白间隙区域
     * @param view       {@link TextView}
     * @param includepad 是否保留字体留白间隙区域
     * @return {@link View}
     */
    public static View setIncludeFontPadding(final View view, final boolean includepad) {
        setIncludeFontPadding(getTextView(view), includepad);
        return view;
    }

    // =

    /**
     * 设置输入类型
     * @param textView {@link TextView}
     * @param type     类型
     * @param <T>      泛型
     * @return {@link TextView}
     */
    public static <T extends TextView> T setInputType(final T textView, final int type) {
        if (textView != null) {
            textView.setInputType(type);
        }
        return textView;
    }

    /**
     * 设置输入类型
     * @param view {@link TextView}
     * @param type 类型
     * @return {@link View}
     */
    public static View setInputType(final View view, final int type) {
        setInputType(getTextView(view), type);
        return view;
    }

    // =

    /**
     * 设置软键盘右下角按钮类型
     * @param textView   {@link TextView}
     * @param imeOptions 软键盘按钮类型
     * @param <T>        泛型
     * @return {@link TextView}
     */
    public static <T extends TextView> T setImeOptions(final T textView, final int imeOptions) {
        if (textView != null) {
            textView.setImeOptions(imeOptions);
        }
        return textView;
    }

    /**
     * 设置软键盘右下角按钮类型
     * @param view       {@link TextView}
     * @param imeOptions 软键盘按钮类型
     * @return {@link View}
     */
    public static View setImeOptions(final View view, final int imeOptions) {
        setImeOptions(getTextView(view), imeOptions);
        return view;
    }

    // =

    /**
     * 设置行数
     * @param textView {@link TextView}
     * @param lines    行数
     * @param <T>      泛型
     */
    public static <T extends TextView> void setLines(final T textView, final int lines) {
        if (textView != null) {
            textView.setLines(lines);
        }
    }

    /**
     * 设置行数
     * @param view  {@link TextView}
     * @param lines 行数
     * @return {@link View}
     */
    public static View setLines(final View view, final int lines) {
        setLines(getTextView(view), lines);
        return view;
    }

    // =

    /**
     * 设置最大行数
     * @param textView {@link TextView}
     * @param maxLines 最大行数
     * @param <T>      泛型
     */
    public static <T extends TextView> void setMaxLines(final T textView, final int maxLines) {
        if (textView != null) {
            textView.setMaxLines(maxLines);
        }
    }

    /**
     * 设置最大行数
     * @param view     {@link TextView}
     * @param maxLines 最大行数
     * @return {@link View}
     */
    public static View setMaxLines(final View view, final int maxLines) {
        setMaxLines(getTextView(view), maxLines);
        return view;
    }

    // =

    /**
     * 设置最小行数
     * @param textView {@link TextView}
     * @param minLines 最小行数
     * @param <T>      泛型
     */
    public static <T extends TextView> void setMinLines(final T textView, final int minLines) {
        if (textView != null && minLines > 0) {
            textView.setMinLines(minLines);
        }
    }

    /**
     * 设置最小行数
     * @param view     {@link TextView}
     * @param minLines 最小行数
     * @return {@link View}
     */
    public static View setMinLines(final View view, final int minLines) {
        setMinLines(getTextView(view), minLines);
        return view;
    }

    // =

    /**
     * 设置最大字符宽度限制
     * @param textView {@link TextView}
     * @param maxEms   最大字符
     * @param <T>      泛型
     * @return {@link TextView}
     */
    public static <T extends TextView> T setMaxEms(final T textView, final int maxEms) {
        if (textView != null && maxEms > 0) {
            textView.setMaxEms(maxEms);
        }
        return textView;
    }

    /**
     * 设置最大字符宽度限制
     * @param view   {@link TextView}
     * @param maxEms 最大字符
     * @return {@link View}
     */
    public static View setMaxEms(final View view, final int maxEms) {
        setMaxEms(getTextView(view), maxEms);
        return view;
    }

    // =

    /**
     * 设置最小字符宽度限制
     * @param textView {@link TextView}
     * @param minEms   最小字符
     * @param <T>      泛型
     * @return {@link TextView}
     */
    public static <T extends TextView> T setMinEms(final T textView, final int minEms) {
        if (textView != null && minEms > 0) {
            textView.setMinEms(minEms);
        }
        return textView;
    }

    /**
     * 设置最小字符宽度限制
     * @param view   {@link TextView}
     * @param minEms 最小字符
     * @return {@link View}
     */
    public static View setMinEms(final View view, final int minEms) {
        setMinEms(getTextView(view), minEms);
        return view;
    }

    // =

    /**
     * 设置指定字符宽度
     * @param textView {@link TextView}
     * @param ems      字符
     * @param <T>      泛型
     * @return {@link TextView}
     */
    public static <T extends TextView> T setEms(final T textView, final int ems) {
        if (textView != null && ems > 0) {
            textView.setEms(ems);
        }
        return textView;
    }

    /**
     * 设置指定字符宽度
     * @param view {@link TextView}
     * @param ems  字符
     * @return {@link View}
     */
    public static View setEms(final View view, final int ems) {
        setEms(getTextView(view), ems);
        return view;
    }

    // =

    /**
     * 设置长度限制
     * @param textView  {@link TextView}
     * @param maxLength 长度限制
     * @param <T>       泛型
     * @return {@link TextView}
     */
    public static <T extends TextView> T setMaxLength(final T textView, final int maxLength) {
        if (textView != null && maxLength > 0) {
            // 设置最大长度限制
            InputFilter[] filters = {new InputFilter.LengthFilter(maxLength)};
            textView.setFilters(filters);
        }
        return textView;
    }

    /**
     * 设置长度限制
     * @param view      {@link TextView}
     * @param maxLength 长度限制
     * @return {@link View}
     */
    public static View setMaxLength(final View view, final int maxLength) {
        setMaxLength(getTextView(view), maxLength);
        return view;
    }

    // =

    /**
     * 设置长度限制, 并且设置内容
     * @param textView  {@link TextView}
     * @param content   文本内容
     * @param maxLength 长度限制
     * @param <T>       泛型
     * @return {@link TextView}
     */
    public static <T extends TextView> T setMaxLengthAndText(final T textView, final String content, final int maxLength) {
        setText(setMaxLength(textView, maxLength), content);
        return textView;
    }

    /**
     * 设置长度限制, 并且设置内容
     * @param view      {@link TextView}
     * @param content   文本内容
     * @param maxLength 长度限制
     * @return {@link View}
     */
    public static View setMaxLengthAndText(final View view, final String content, final int maxLength) {
        return setText(setMaxLength(view, maxLength), content);
    }

    // =

    /**
     * 设置 Ellipsize 效果
     * @param textView {@link TextView}
     * @param where    {@link TextUtils.TruncateAt}
     * @param <T>      泛型
     */
    public static <T extends TextView> void setEllipsize(final T textView, final TextUtils.TruncateAt where) {
        if (textView != null) {
            textView.setEllipsize(where);
        }
    }

    /**
     * 设置 Ellipsize 效果
     * @param view  {@link TextView}
     * @param where {@link TextUtils.TruncateAt}
     * @return {@link View}
     */
    public static View setEllipsize(final View view, final TextUtils.TruncateAt where) {
        setEllipsize(getTextView(view), where);
        return view;
    }

    // =

    /**
     * 设置自动识别文本链接
     * @param textView {@link TextView}
     * @param mask     {@link android.text.util.Linkify}
     * @param <T>      泛型
     */
    public static <T extends TextView> void setAutoLinkMask(final T textView, final int mask) {
        if (textView != null) {
            textView.setAutoLinkMask(mask);
        }
    }

    /**
     * 设置自动识别文本链接
     * @param view {@link TextView}
     * @param mask {@link android.text.util.Linkify}
     * @return {@link View}
     */
    public static View setAutoLinkMask(final View view, final int mask) {
        setAutoLinkMask(getTextView(view), mask);
        return view;
    }

    // =

    /**
     * 设置文本全为大写
     * @param textView {@link TextView}
     * @param allCaps  是否全部大写
     * @param <T>      泛型
     */
    public static <T extends TextView> void setAllCaps(final T textView, final boolean allCaps) {
        if (textView != null) {
            textView.setAllCaps(allCaps);
        }
    }

    /**
     * 设置文本全为大写
     * @param view    {@link TextView}
     * @param allCaps 是否全部大写
     * @return {@link View}
     */
    public static View setAllCaps(final View view, final boolean allCaps) {
        setAllCaps(getTextView(view), allCaps);
        return view;
    }

    // =

    /**
     * 设置 Gravity
     * @param textView {@link TextView}
     * @param gravity  {@link android.view.Gravity}
     * @param <T>      泛型
     */
    public static <T extends TextView> void setGravity(final T textView, final int gravity) {
        if (textView != null) {
            textView.setGravity(gravity);
        }
    }

    /**
     * 设置 Gravity
     * @param view    {@link TextView}
     * @param gravity {@link android.view.Gravity}
     * @return {@link View}
     */
    public static View setGravity(final View view, final int gravity) {
        setGravity(getTextView(view), gravity);
        return view;
    }

    // =

    /**
     * 获取 TextView Paint
     * @param view {@link TextView}
     * @param <T>  泛型
     * @return {@link Paint}
     */
    public static <T extends TextView> Paint getPaint(final View view) {
        return getPaint(getTextView(view));
    }

    /**
     * 获取 TextView Paint
     * @param textView {@link TextView}
     * @param <T>      泛型
     * @return {@link Paint}
     */
    public static <T extends TextView> Paint getPaint(final T textView) {
        if (textView != null) {
            return textView.getPaint();
        }
        return null;
    }

    /**
     * 获取字体高度
     * @param textView {@link TextView}
     * @param <T>      泛型
     * @return 字体高度
     */
    public static <T extends TextView> int getTextHeight(final T textView) {
        return getTextHeight(getPaint(textView));
    }

    /**
     * 获取字体高度
     * @param paint {@link TextView#getPaint()}
     * @return 字体高度
     */
    public static int getTextHeight(final Paint paint) {
        if (paint != null) {
            // 获取字体高度
            Paint.FontMetricsInt fontMetrics = paint.getFontMetricsInt();
            // 计算内容高度
            int textHeight = (int) Math.ceil((fontMetrics.descent - fontMetrics.ascent));
            // 返回字体高度
            return textHeight;
        }
        return -1;
    }

    // =

    /**
     * 获取字体顶部偏移高度
     * @param textView {@link TextView}
     * @param <T>      泛型
     * @return 字体顶部偏移高度
     */
    public static <T extends TextView> int getTextTopOffsetHeight(final T textView) {
        return getTextTopOffsetHeight(getPaint(textView));
    }

    /**
     * 获取字体顶部偏移高度
     * @param paint {@link TextView#getPaint()}
     * @return 字体顶部偏移高度
     */
    public static int getTextTopOffsetHeight(final Paint paint) {
        if (paint != null) {
            // 获取字体高度
            Paint.FontMetricsInt fontMetrics = paint.getFontMetricsInt();
            // 计算字体偏差 ( 顶部偏差 )
            int baseLine = (int) Math.ceil(Math.abs(fontMetrics.top) - Math.abs(fontMetrics.ascent));
            // 返回顶部偏差
            return baseLine;
        }
        return -1;
    }

    // =

    /**
     * 计算字体宽度
     * @param textView {@link TextView}
     * @param text     待测量文本
     * @param <T>      泛型
     * @return 字体宽度
     */
    public static <T extends TextView> float getTextWidth(final T textView, final String text) {
        return getTextWidth(getPaint(textView), text);
    }

    /**
     * 计算字体宽度
     * @param textView {@link TextView}
     * @param <T>      泛型
     * @return 字体宽度
     */
    public static <T extends TextView> float getTextWidth(final T textView) {
        return getTextWidth(getPaint(textView), getText(textView));
    }

    /**
     * 计算字体宽度
     * @param paint {@link TextView#getPaint()}
     * @param text  待测量文本
     * @return 字体宽度
     */
    public static float getTextWidth(final Paint paint, final String text) {
        if (paint != null && text != null) {
            return paint.measureText(text);
        }
        return -1f;
    }

    // =

    /**
     * 计算字体宽度
     * @param view  {@link TextView}
     * @param text  待测量文本
     * @param start 开始位置
     * @param end   结束位置
     * @return 字体宽度
     */
    public static float getTextWidth(final View view, final String text, final int start, final int end) {
        return getTextWidth(getPaint(view), text, start, end);
    }

    /**
     * 计算字体宽度
     * @param view  {@link TextView}
     * @param text  待测量文本
     * @param start 开始位置
     * @param end   结束位置
     * @return 字体宽度
     */
    public static float getTextWidth(final View view, final CharSequence text, final int start, final int end) {
        return getTextWidth(getPaint(view), text, start, end);
    }

    /**
     * 计算字体宽度
     * @param view  {@link TextView}
     * @param text  待测量文本
     * @param start 开始位置
     * @param end   结束位置
     * @return 字体宽度
     */
    public static float getTextWidth(final View view, final char[] text, final int start, final int end) {
        return getTextWidth(getPaint(view), text, start, end);
    }

    // =

    /**
     * 计算字体宽度
     * @param paint {@link TextView#getPaint()}
     * @param text  待测量文本
     * @param start 开始位置
     * @param end   结束位置
     * @return 字体宽度
     */
    public static float getTextWidth(final Paint paint, final String text, final int start, final int end) {
        if (paint != null && text != null) {
            try {
                return paint.measureText(text, start, end);
            } catch (Exception e) {
                LogPrintUtils.eTag(TAG, e, "getTextWidth");
            }
        }
        return -1f;
    }

    /**
     * 计算字体宽度
     * @param paint {@link TextView#getPaint()}
     * @param text  待测量文本
     * @param start 开始位置
     * @param end   结束位置
     * @return 字体宽度
     */
    public static float getTextWidth(final Paint paint, final CharSequence text, final int start, final int end) {
        if (paint != null && text != null) {
            try {
                return paint.measureText(text, start, end);
            } catch (Exception e) {
                LogPrintUtils.eTag(TAG, e, "getTextWidth");
            }
        }
        return -1f;
    }

    /**
     * 计算字体宽度
     * @param paint {@link TextView#getPaint()}
     * @param text  待测量文本
     * @param start 开始位置
     * @param end   结束位置
     * @return 字体宽度
     */
    public static float getTextWidth(final Paint paint, final char[] text, final int start, final int end) {
        if (paint != null && text != null) {
            try {
                return paint.measureText(text, start, end);
            } catch (Exception e) {
                LogPrintUtils.eTag(TAG, e, "getTextWidth");
            }
        }
        return -1f;
    }

    // =

    /**
     * 获取画布中间居中位置
     * @param targetRect {@link Rect} 目标坐标
     * @param paint      {@link TextView#getPaint()}
     * @return 画布 Y 轴居中位置
     */
    public static int getCenterRectY(final Rect targetRect, final Paint paint) {
        if (targetRect != null && paint != null) {
            // 获取字体高度
            Paint.FontMetricsInt fontMetrics = paint.getFontMetricsInt();
            // 获取底部 Y 轴居中位置
            return targetRect.top + (targetRect.bottom - targetRect.top) / 2 - (fontMetrics.bottom - fontMetrics.top) / 2 - fontMetrics.top;
            // canvas.drawText(testString, targetRect.centerX(), baseline, paint);
        }
        return -1;
    }

    /**
     * 通过需要的高度, 计算字体大小
     * @param textHeight 需要的字体高度
     * @return 字体大小
     */
    public static float reckonTextSize(final int textHeight) {
        // 创建画笔
        Paint paint = new Paint();
        // 默认字体大小
        float textSize = 90.0f;
        // 计算内容高度
        int calcTextHeight = -1;
        // 循环计算
        while (true) {
            // 设置画笔大小
            paint.setTextSize(textSize);
            // 获取字体高度
            Paint.FontMetricsInt fontMetrics = paint.getFontMetricsInt();
            // 计算内容高度
            calcTextHeight = (int) Math.ceil((fontMetrics.descent - fontMetrics.ascent));
            // 符合条件则直接返回
            if (calcTextHeight == textHeight) {
                return textSize;
            } else if (calcTextHeight > textHeight) { // 如果计算的字体高度大于
                textSize -= 0.5f;
            } else {
                textSize += 0.5f;
            }
        }
    }

    // =

    /**
     * 计算第几位超过宽度
     * @param textView {@link TextView}
     * @param text     待测量文本
     * @param width    指定的宽度
     * @param <T>      泛型
     * @return -1 表示没超过, 其他值表示对应的索引位置
     */
    public static <T extends TextView> int calcTextWidth(final T textView, final String text, final float width) {
        return calcTextWidth(getPaint(textView), text, width);
    }

    /**
     * 计算第几位超过宽度
     * @param textView {@link TextView}
     * @param width    指定的宽度
     * @param <T>      泛型
     * @return -1 表示没超过, 其他值表示对应的索引位置
     */
    public static <T extends TextView> int calcTextWidth(final T textView, final float width) {
        return calcTextWidth(getPaint(textView), getText(textView), width);
    }

    /**
     * 计算第几位超过宽度
     * @param paint {@link TextView#getPaint()}
     * @param text  文本内容
     * @param width 指定的宽度
     * @return -1 表示没超过, 其他值表示对应的索引位置
     */
    public static int calcTextWidth(final Paint paint, final String text, final float width) {
        if (paint != null && text != null && width > 0) {
            // 全部文本宽度
            float allTextWidth = getTextWidth(paint, text);
            // 判断是否超过
            if (allTextWidth <= width) return -1; // 表示没超过
            // 获取数据长度
            int length = text.length();
            // 超过长度且只有一个数据, 那么只能是第一个就超过了
            if (length == 1) return 1;
            // 二分法寻找最佳位置
            int start = 0;
            int end = length;
            int mid = 0;
            // 判断是否大于位置
            while (start < end) {
                mid = (start + end) / 2;
                // 获取字体宽度
                float textWidth = getTextWidth(paint, text, 0, mid);
                // 如果相等直接返回
                if (textWidth == width) {
                    return mid;
                } else if (textWidth > width) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            }
            // 计算最符合的位置
            for (int i = Math.min(Math.min(start, mid), end), len = length; i <= len; i++) {
                float textWidth = TextViewUtils.getTextWidth(paint, text, 0, i);
                if (textWidth >= width) return i;
            }
            return start;
        }
        return -1;
    }

    /**
     * 计算文本换行行数
     * @param textView {@link TextView}
     * @param text     待测量文本
     * @param width    指定的宽度
     * @param <T>      泛型
     * @return 行数
     */
    public static <T extends TextView> int calcTextLine(final T textView, final String text, final float width) {
        return calcTextLine(getPaint(textView), text, width);
    }

    /**
     * 计算文本行数
     * @param textView {@link TextView}
     * @param width    指定的宽度
     * @param <T>      泛型
     * @return 行数
     */
    public static <T extends TextView> int calcTextLine(final T textView, final float width) {
        return calcTextLine(getPaint(textView), getText(textView), width);
    }

    /**
     * 计算文本行数
     * @param paint {@link TextView#getPaint()}
     * @param text  文本内容
     * @param width 指定的宽度
     * @return 行数
     */
    public static int calcTextLine(final Paint paint, final String text, final float width) {
        if (paint != null && text != null && width > 0) {
            // 全部文本宽度
            float allTextWidth = getTextWidth(paint, text);
            // 判断是否超过
            if (allTextWidth <= width) return 1;
            int result = (int) (allTextWidth / width);
            return ((allTextWidth - width * result == 0f) ? result : result + 1);
        }
        return 0;
    }
}