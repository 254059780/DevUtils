package dev.utils.app.helper.view;

import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.KeyListener;
import android.text.method.TransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;

import dev.utils.app.EditTextUtils;
import dev.utils.app.HandlerUtils;
import dev.utils.app.ImageViewUtils;
import dev.utils.app.ListViewUtils;
import dev.utils.app.TextViewUtils;
import dev.utils.app.helper.dev.DevHelper;
import dev.utils.app.helper.quick.QuickHelper;

/**
 * detail: View 链式调用快捷设置 Helper 类
 * @author Ttt
 * <pre>
 *     通过 DevApp 工具类快捷实现
 *     <p></p>
 *     DevApp Api
 *     @see <a href="https://github.com/afkT/DevUtils/blob/master/lib/DevApp/README.md"/>
 * </pre>
 */
public final class ViewHelper {

    // ViewHelper
    private static final ViewHelper HELPER = new ViewHelper();

    /**
     * 获取单例 ViewHelper
     * @return {@link ViewHelper}
     */
    public static ViewHelper get() {
        return HELPER;
    }

    // ==========
    // = Helper =
    // ==========

    /**
     * 获取 ViewHelper
     * @return {@link ViewHelper}
     */
    public ViewHelper viewHelper() {
        return HELPER;
    }

    /**
     * 获取 DevHelper
     * @return {@link DevHelper}
     */
    public DevHelper devHelper() {
        return DevHelper.get();
    }

    /**
     * 获取 QuickHelper
     * @param target 目标 View
     * @return {@link QuickHelper}
     */
    public QuickHelper quickHelper(final View target) {
        return QuickHelper.get(target);
    }

    // ===========
    // = Handler =
    // ===========

    /**
     * 在主线程 Handler 中执行任务
     * @param runnable 可执行的任务
     * @return {@link ViewHelper}
     */
    public ViewHelper postRunnable(final Runnable runnable) {
        HandlerUtils.postRunnable(runnable);
        return this;
    }

    /**
     * 在主线程 Handler 中执行延迟任务
     * @param runnable    可执行的任务
     * @param delayMillis 延迟时间
     * @return {@link ViewHelper}
     */
    public ViewHelper postRunnable(
            final Runnable runnable,
            final long delayMillis
    ) {
        HandlerUtils.postRunnable(runnable, delayMillis);
        return this;
    }

    /**
     * 在主线程 Handler 中执行延迟任务
     * @param runnable    可执行的任务
     * @param delayMillis 延迟时间
     * @param number      轮询次数
     * @param interval    轮询时间
     * @return {@link ViewHelper}
     */
    public ViewHelper postRunnable(
            final Runnable runnable,
            final long delayMillis,
            final int number,
            final int interval
    ) {
        HandlerUtils.postRunnable(runnable, delayMillis, number, interval);
        return this;
    }

    /**
     * 在主线程 Handler 中执行延迟任务
     * @param runnable      可执行的任务
     * @param delayMillis   延迟时间
     * @param number        轮询次数
     * @param interval      轮询时间
     * @param onEndListener 结束通知
     * @return {@link ViewHelper}
     */
    public ViewHelper postRunnable(
            final Runnable runnable,
            final long delayMillis,
            final int number,
            final int interval,
            final HandlerUtils.OnEndListener onEndListener
    ) {
        HandlerUtils.postRunnable(runnable, delayMillis, number, interval, onEndListener);
        return this;
    }

    /**
     * 在主线程 Handler 中清除任务
     * @param runnable 需要清除的任务
     * @return {@link ViewHelper}
     */
    public ViewHelper removeRunnable(final Runnable runnable) {
        HandlerUtils.removeRunnable(runnable);
        return this;
    }

    // ========
    // = Text =
    // ========

    /**
     * 设置 Hint 文本
     * @param view {@link TextView}
     * @param text Hint text
     * @return {@link ViewHelper}
     */
    public ViewHelper setHint(
            final View view,
            final CharSequence text
    ) {
        TextViewUtils.setHint(view, text);
        return this;
    }

    /**
     * 设置文本
     * @param view {@link TextView}
     * @param text TextView text
     * @return {@link ViewHelper}
     */
    public ViewHelper setText(
            final View view,
            final CharSequence text
    ) {
        if (view instanceof EditText) {
            EditTextUtils.setText(EditTextUtils.getEditText(view), text);
        } else {
            TextViewUtils.setText(view, text);
        }
        return this;
    }

    /**
     * 设置多个 TextView 文本
     * @param text  TextView text
     * @param views View(TextView)[]
     * @return {@link ViewHelper}
     */
    public ViewHelper setTexts(
            final CharSequence text,
            final View... views
    ) {
        TextViewUtils.setTexts(text, views);
        return this;
    }

    /**
     * 设置 Html 内容
     * @param view    {@link TextView}
     * @param content Html content
     * @return {@link ViewHelper}
     */
    public ViewHelper setHtmlText(
            final View view,
            final String content
    ) {
        TextViewUtils.setHtmlText(view, content);
        return this;
    }

    /**
     * 设置多个 TextView Html 内容
     * @param content Html content
     * @param views   View(TextView)[]
     * @return {@link ViewHelper}
     */
    public ViewHelper setHtmlTexts(
            final String content,
            final View... views
    ) {
        TextViewUtils.setHtmlTexts(content, views);
        return this;
    }

    /**
     * 设置 Hint 字体颜色
     * @param view  {@link TextView}
     * @param color R.color.id
     * @return {@link ViewHelper}
     */
    public ViewHelper setHintTextColor(
            final View view,
            @ColorInt final int color
    ) {
        TextViewUtils.setHintTextColor(view, color);
        return this;
    }

    /**
     * 设置 Hint 字体颜色
     * @param view   {@link TextView}
     * @param colors {@link ColorStateList}
     * @return {@link ViewHelper}
     */
    public ViewHelper setHintTextColor(
            final View view,
            final ColorStateList colors
    ) {
        TextViewUtils.setHintTextColor(view, colors);
        return this;
    }

    /**
     * 设置多个 TextView Hint 字体颜色
     * @param color R.color.id
     * @param views View(TextView)[]
     * @return {@link ViewHelper}
     */
    public ViewHelper setHintTextColors(
            @ColorInt final int color,
            final View... views
    ) {
        TextViewUtils.setHintTextColors(color, views);
        return this;
    }

    /**
     * 设置多个 TextView Hint 字体颜色
     * @param colors {@link ColorStateList}
     * @param views  View(TextView)[]
     * @return {@link ViewHelper}
     */
    public ViewHelper setHintTextColors(
            final ColorStateList colors,
            final View... views
    ) {
        TextViewUtils.setHintTextColors(colors, views);
        return this;
    }

    /**
     * 设置字体颜色
     * @param view  {@link TextView}
     * @param color R.color.id
     * @return {@link ViewHelper}
     */
    public ViewHelper setTextColor(
            final View view,
            @ColorInt final int color
    ) {
        TextViewUtils.setTextColor(view, color);
        return this;
    }

    /**
     * 设置字体颜色
     * @param view   {@link TextView}
     * @param colors {@link ColorStateList}
     * @return {@link ViewHelper}
     */
    public ViewHelper setTextColor(
            final View view,
            final ColorStateList colors
    ) {
        TextViewUtils.setTextColor(view, colors);
        return this;
    }

    /**
     * 设置多个 TextView 字体颜色
     * @param color R.color.id
     * @param views View(TextView)[]
     * @return {@link ViewHelper}
     */
    public ViewHelper setTextColors(
            @ColorInt final int color,
            final View... views
    ) {
        TextViewUtils.setTextColors(color, views);
        return this;
    }

    /**
     * 设置多个 TextView 字体颜色
     * @param colors {@link ColorStateList}
     * @param views  View(TextView)[]
     * @return {@link ViewHelper}
     */
    public ViewHelper setTextColors(
            final ColorStateList colors,
            final View... views
    ) {
        TextViewUtils.setTextColors(colors, views);
        return this;
    }

    /**
     * 设置字体
     * @param view     {@link TextView}
     * @param typeface {@link Typeface} 字体样式
     * @return {@link ViewHelper}
     */
    public ViewHelper setTypeface(
            final View view,
            final Typeface typeface
    ) {
        TextViewUtils.setTypeface(view, typeface);
        return this;
    }

    /**
     * 设置字体大小 ( px 像素 )
     * @param view {@link TextView}
     * @param size 字体大小
     * @return {@link ViewHelper}
     */
    public ViewHelper setTextSizeByPx(
            final View view,
            final float size
    ) {
        TextViewUtils.setTextSizeByPx(view, size);
        return this;
    }

    /**
     * 设置字体大小 ( sp 缩放像素 )
     * @param view {@link TextView}
     * @param size 字体大小
     * @return {@link ViewHelper}
     */
    public ViewHelper setTextSizeBySp(
            final View view,
            final float size
    ) {
        TextViewUtils.setTextSizeBySp(view, size);
        return this;
    }

    /**
     * 设置字体大小 ( dp 与设备无关的像素 )
     * @param view {@link TextView}
     * @param size 字体大小
     * @return {@link ViewHelper}
     */
    public ViewHelper setTextSizeByDp(
            final View view,
            final float size
    ) {
        TextViewUtils.setTextSizeByDp(view, size);
        return this;
    }

    /**
     * 设置字体大小 ( inches 英寸 )
     * @param view {@link TextView}
     * @param size 字体大小
     * @return {@link ViewHelper}
     */
    public ViewHelper setTextSizeByIn(
            final View view,
            final float size
    ) {
        TextViewUtils.setTextSizeByIn(view, size);
        return this;
    }

    /**
     * 设置字体大小
     * @param view {@link TextView}
     * @param unit 字体参数类型
     * @param size 字体大小
     * @return {@link ViewHelper}
     */
    public ViewHelper setTextSize(
            final View view,
            final int unit,
            final float size
    ) {
        TextViewUtils.setTextSize(view, unit, size);
        return this;
    }

    /**
     * 设置多个 TextView 字体大小
     * @param views View(TextView)[]
     * @param unit  参数类型
     * @param size  字体大小
     * @return {@link ViewHelper}
     */
    public ViewHelper setTextSizes(
            final View[] views,
            final int unit,
            final float size
    ) {
        TextViewUtils.setTextSizes(views, unit, size);
        return this;
    }

    /**
     * 清空 flags
     * @param view {@link TextView}
     * @return {@link ViewHelper}
     */
    public ViewHelper clearFlags(final View view) {
        TextViewUtils.clearFlags(view);
        return this;
    }

    /**
     * 设置 TextView flags
     * @param view  {@link TextView}
     * @param flags flags
     * @return {@link ViewHelper}
     */
    public ViewHelper setPaintFlags(
            final View view,
            final int flags
    ) {
        TextViewUtils.setPaintFlags(view, flags);
        return this;
    }

    /**
     * 设置 TextView 抗锯齿 flags
     * @param view {@link TextView}
     * @return {@link ViewHelper}
     */
    public ViewHelper setAntiAliasFlag(final View view) {
        TextViewUtils.setAntiAliasFlag(view);
        return this;
    }

    /**
     * 设置 TextView 是否加粗
     * @param view {@link TextView}
     * @return {@link ViewHelper}
     */
    public ViewHelper setBold(final View view) {
        TextViewUtils.setBold(view);
        return this;
    }

    /**
     * 设置 TextView 是否加粗
     * @param view   {@link TextView}
     * @param isBold {@code true} yes, {@code false} no
     * @return {@link ViewHelper}
     */
    public ViewHelper setBold(
            final View view,
            final boolean isBold
    ) {
        TextViewUtils.setBold(view, isBold);
        return this;
    }

    /**
     * 设置 TextView 是否加粗
     * @param view     {@link TextView}
     * @param typeface {@link Typeface} 字体样式
     * @param isBold   {@code true} yes, {@code false} no
     * @return {@link ViewHelper}
     */
    public ViewHelper setBold(
            final View view,
            final Typeface typeface,
            final boolean isBold
    ) {
        TextViewUtils.setBold(view, typeface, isBold);
        return this;
    }

    /**
     * 设置下划线
     * @param view {@link TextView}
     * @return {@link ViewHelper}
     */
    public ViewHelper setUnderlineText(final View view) {
        TextViewUtils.setUnderlineText(view);
        return this;
    }

    /**
     * 设置下划线并加清晰
     * @param view        {@link TextView}
     * @param isAntiAlias 是否消除锯齿
     * @return {@link ViewHelper}
     */
    public ViewHelper setUnderlineText(
            final View view,
            final boolean isAntiAlias
    ) {
        TextViewUtils.setUnderlineText(view, isAntiAlias);
        return this;
    }

    /**
     * 设置中划线
     * @param view {@link TextView}
     * @return {@link ViewHelper}
     */
    public ViewHelper setStrikeThruText(final View view) {
        TextViewUtils.setStrikeThruText(view);
        return this;
    }

    /**
     * 设置中划线并加清晰
     * @param view        {@link TextView}
     * @param isAntiAlias 是否消除锯齿
     * @return {@link ViewHelper}
     */
    public ViewHelper setStrikeThruText(
            final View view,
            final boolean isAntiAlias
    ) {
        TextViewUtils.setStrikeThruText(view, isAntiAlias);
        return this;
    }

    /**
     * 设置文字水平间距
     * @param view          {@link TextView}
     * @param letterSpacing 文字水平间距值
     * @return {@link ViewHelper}
     */
    public ViewHelper setLetterSpacing(
            final View view,
            final float letterSpacing
    ) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            TextViewUtils.setLetterSpacing(view, letterSpacing);
        }
        return this;
    }

    /**
     * 设置文字行间距 ( 行高 )
     * @param view        {@link TextView}
     * @param lineSpacing 文字行间距 ( 行高 ), android:lineSpacingExtra
     * @return {@link ViewHelper}
     */
    public ViewHelper setLineSpacing(
            final View view,
            final float lineSpacing
    ) {
        TextViewUtils.setLineSpacing(view, lineSpacing);
        return this;
    }

    /**
     * 设置文字行间距 ( 行高 )、行间距倍数
     * @param view        {@link TextView}
     * @param lineSpacing 文字行间距 ( 行高 ), android:lineSpacingExtra
     * @param multiplier  行间距倍数, android:lineSpacingMultiplier
     * @return {@link ViewHelper}
     */
    public ViewHelper setLineSpacingAndMultiplier(
            final View view,
            final float lineSpacing,
            final float multiplier
    ) {
        TextViewUtils.setLineSpacingAndMultiplier(view, lineSpacing, multiplier);
        return this;
    }

    /**
     * 设置字体水平方向的缩放
     * @param view {@link TextView}
     * @param size 缩放比例
     * @return {@link ViewHelper}
     */
    public ViewHelper setTextScaleX(
            final View view,
            final float size
    ) {
        TextViewUtils.setTextScaleX(view, size);
        return this;
    }

    /**
     * 设置是否保留字体留白间隙区域
     * @param view           {@link TextView}
     * @param includePadding 是否保留字体留白间隙区域
     * @return {@link ViewHelper}
     */
    public ViewHelper setIncludeFontPadding(
            final View view,
            final boolean includePadding
    ) {
        TextViewUtils.setIncludeFontPadding(view, includePadding);
        return this;
    }

    /**
     * 设置输入类型
     * @param view {@link TextView}
     * @param type 类型
     * @return {@link ViewHelper}
     */
    public ViewHelper setInputType(
            final View view,
            final int type
    ) {
        TextViewUtils.setInputType(view, type);
        return this;
    }

    /**
     * 设置软键盘右下角按钮类型
     * @param view       {@link TextView}
     * @param imeOptions 软键盘按钮类型
     * @return {@link ViewHelper}
     */
    public ViewHelper setImeOptions(
            final View view,
            final int imeOptions
    ) {
        TextViewUtils.setImeOptions(view, imeOptions);
        return this;
    }

    /**
     * 设置行数
     * @param view  {@link TextView}
     * @param lines 行数
     * @return {@link ViewHelper}
     */
    public ViewHelper setLines(
            final View view,
            final int lines
    ) {
        TextViewUtils.setLines(view, lines);
        return this;
    }

    /**
     * 设置最大行数
     * @param view     {@link TextView}
     * @param maxLines 最大行数
     * @return {@link ViewHelper}
     */
    public ViewHelper setMaxLines(
            final View view,
            final int maxLines
    ) {
        TextViewUtils.setMaxLines(view, maxLines);
        return this;
    }

    /**
     * 设置最小行数
     * @param view     {@link TextView}
     * @param minLines 最小行数
     * @return {@link ViewHelper}
     */
    public ViewHelper setMinLines(
            final View view,
            final int minLines
    ) {
        TextViewUtils.setMinLines(view, minLines);
        return this;
    }

    /**
     * 设置最大字符宽度限制
     * @param view   {@link TextView}
     * @param maxEms 最大字符
     * @return {@link ViewHelper}
     */
    public ViewHelper setMaxEms(
            final View view,
            final int maxEms
    ) {
        TextViewUtils.setMaxEms(view, maxEms);
        return this;
    }

    /**
     * 设置最小字符宽度限制
     * @param view   {@link TextView}
     * @param minEms 最小字符
     * @return {@link ViewHelper}
     */
    public ViewHelper setMinEms(
            final View view,
            final int minEms
    ) {
        TextViewUtils.setMinEms(view, minEms);
        return this;
    }

    /**
     * 设置指定字符宽度
     * @param view {@link TextView}
     * @param ems  字符
     * @return {@link ViewHelper}
     */
    public ViewHelper setEms(
            final View view,
            final int ems
    ) {
        TextViewUtils.setEms(view, ems);
        return this;
    }

    /**
     * 设置 Ellipsize 效果
     * @param view  {@link TextView}
     * @param where {@link TextUtils.TruncateAt}
     * @return {@link ViewHelper}
     */
    public ViewHelper setEllipsize(
            final View view,
            final TextUtils.TruncateAt where
    ) {
        TextViewUtils.setEllipsize(view, where);
        return this;
    }

    /**
     * 设置自动识别文本链接
     * @param view {@link TextView}
     * @param mask {@link android.text.util.Linkify}
     * @return {@link ViewHelper}
     */
    public ViewHelper setAutoLinkMask(
            final View view,
            final int mask
    ) {
        TextViewUtils.setAutoLinkMask(view, mask);
        return this;
    }

    /**
     * 设置文本全为大写
     * @param view    {@link TextView}
     * @param allCaps 是否全部大写
     * @return {@link ViewHelper}
     */
    public ViewHelper setAllCaps(
            final View view,
            final boolean allCaps
    ) {
        TextViewUtils.setAllCaps(view, allCaps);
        return this;
    }

    /**
     * 设置 Text Gravity
     * @param view    {@link TextView}
     * @param gravity {@link android.view.Gravity}
     * @return {@link ViewHelper}
     */
    public ViewHelper setTextGravity(
            final View view,
            final int gravity
    ) {
        TextViewUtils.setGravity(view, gravity);
        return this;
    }

    // ============
    // = EditText =
    // ============

    /**
     * 设置内容
     * @param editText {@link EditText}
     * @param content  文本内容
     * @param isSelect 是否设置光标
     * @return {@link ViewHelper}
     */
    public ViewHelper setText(
            final EditText editText,
            final CharSequence content,
            final boolean isSelect
    ) {
        EditTextUtils.setText(editText, content, isSelect);
        return this;
    }

    /**
     * 追加内容 ( 当前光标位置追加 )
     * @param editText {@link EditText}
     * @param content  文本内容
     * @param isSelect 是否设置光标
     * @return {@link ViewHelper}
     */
    public ViewHelper insert(
            final EditText editText,
            final CharSequence content,
            final boolean isSelect
    ) {
        EditTextUtils.insert(editText, content, isSelect);
        return this;
    }

    /**
     * 追加内容
     * @param editText {@link EditText}
     * @param content  文本内容
     * @param start    开始添加的位置
     * @param isSelect 是否设置光标
     * @return {@link ViewHelper}
     */
    public ViewHelper insert(
            final EditText editText,
            final CharSequence content,
            final int start,
            final boolean isSelect
    ) {
        EditTextUtils.insert(editText, content, start, isSelect);
        return this;
    }

    /**
     * 设置长度限制
     * @param view      {@link View}
     * @param maxLength 长度限制
     * @return {@link ViewHelper}
     */
    public ViewHelper setMaxLength(
            final View view,
            final int maxLength
    ) {
        if (view instanceof EditText) {
            EditTextUtils.setMaxLength(EditTextUtils.getEditText(view), maxLength);
        } else {
            TextViewUtils.setMaxLength(view, maxLength);
        }
        return this;
    }

    /**
     * 设置长度限制, 并且设置内容
     * @param view      {@link View}
     * @param content   文本内容
     * @param maxLength 长度限制
     * @return {@link ViewHelper}
     */
    public ViewHelper setMaxLengthAndText(
            final View view,
            final CharSequence content,
            final int maxLength
    ) {
        if (view instanceof EditText) {
            EditTextUtils.setMaxLengthAndText(EditTextUtils.getEditText(view), content, maxLength);
        } else {
            TextViewUtils.setMaxLengthAndText(view, content, maxLength);
        }
        return this;
    }

    /**
     * 设置是否显示光标
     * @param editText {@link EditText}
     * @param visible  是否显示光标
     * @return {@link ViewHelper}
     */
    public ViewHelper setCursorVisible(
            final EditText editText,
            final boolean visible
    ) {
        EditTextUtils.setCursorVisible(editText, visible);
        return this;
    }

    /**
     * 设置光标在第一位
     * @param editText {@link EditText}
     * @return {@link ViewHelper}
     */
    public ViewHelper setSelectionToTop(final EditText editText) {
        EditTextUtils.setSelectionToTop(editText);
        return this;
    }

    /**
     * 设置光标在最后一位
     * @param editText {@link EditText}
     * @return {@link ViewHelper}
     */
    public ViewHelper setSelectionToBottom(final EditText editText) {
        EditTextUtils.setSelectionToBottom(editText);
        return this;
    }

    /**
     * 设置光标位置
     * @param editText {@link EditText}
     * @param index    光标位置
     * @return {@link ViewHelper}
     */
    public ViewHelper setSelection(
            final EditText editText,
            final int index
    ) {
        EditTextUtils.setSelection(editText, index);
        return this;
    }

    // =

    /**
     * 设置文本视图显示转换
     * @param view   {@link View}
     * @param method {@link TransformationMethod}
     * @return {@link ViewHelper}
     */
    public ViewHelper setTransformationMethod(
            final View view,
            final TransformationMethod method
    ) {
        if (view instanceof EditText) {
            EditTextUtils.setTransformationMethod(EditTextUtils.getEditText(view), method);
        } else {
            TextViewUtils.setTransformationMethod(view, method);
        }
        return this;
    }

    /**
     * 设置密码文本视图显示转换
     * @param view              {@link View}
     * @param isDisplayPassword 是否显示密码
     * @return {@link ViewHelper}
     */
    public ViewHelper setTransformationMethod(
            final View view,
            final boolean isDisplayPassword
    ) {
        if (view instanceof EditText) {
            EditTextUtils.setTransformationMethod(EditTextUtils.getEditText(view), isDisplayPassword);
        } else {
            TextViewUtils.setTransformationMethod(view, isDisplayPassword);
        }
        return this;
    }

    /**
     * 设置密码文本视图显示转换
     * @param editText          {@link EditText}
     * @param isDisplayPassword 是否显示密码
     * @param isSelectBottom    是否设置光标到最后
     * @return {@link ViewHelper}
     */
    public ViewHelper setTransformationMethod(
            final EditText editText,
            final boolean isDisplayPassword,
            final boolean isSelectBottom
    ) {
        EditTextUtils.setTransformationMethod(editText, isDisplayPassword, isSelectBottom);
        return this;
    }

    // =

    /**
     * 添加输入监听事件
     * @param editText {@link EditText}
     * @param watcher  输入监听
     * @return {@link ViewHelper}
     */
    public ViewHelper addTextChangedListener(
            final EditText editText,
            final TextWatcher watcher
    ) {
        EditTextUtils.addTextChangedListener(editText, watcher);
        return this;
    }

    /**
     * 移除输入监听事件
     * @param editText {@link EditText}
     * @param watcher  输入监听
     * @return {@link ViewHelper}
     */
    public ViewHelper removeTextChangedListener(
            final EditText editText,
            final TextWatcher watcher
    ) {
        EditTextUtils.removeTextChangedListener(editText, watcher);
        return this;
    }

    /**
     * 设置 KeyListener
     * @param editText    {@link EditText}
     * @param keyListener {@link KeyListener}
     * @return {@link ViewHelper}
     */
    public ViewHelper setKeyListener(
            final EditText editText,
            final KeyListener keyListener
    ) {
        EditTextUtils.setKeyListener(editText, keyListener);
        return this;
    }

    /**
     * 设置 KeyListener
     * @param editText {@link EditText}
     * @param accepted 允许输入的内容, 如: 0123456789
     * @return {@link ViewHelper}
     */
    public ViewHelper setKeyListener(
            final EditText editText,
            final String accepted
    ) {
        EditTextUtils.setKeyListener(editText, accepted);
        return this;
    }

    /**
     * 设置 KeyListener
     * @param editText {@link EditText}
     * @param accepted 允许输入的内容
     * @return {@link ViewHelper}
     */
    public ViewHelper setKeyListener(
            final EditText editText,
            final char[] accepted
    ) {
        EditTextUtils.setKeyListener(editText, accepted);
        return this;
    }

    // =========
    // = Image =
    // =========

    /**
     * 设置 ImageView 是否保持宽高比
     * @param imageView        ImageView
     * @param adjustViewBounds 是否调整此视图的边界以保持可绘制的原始纵横比
     * @return {@link ViewHelper}
     */
    public ViewHelper setAdjustViewBounds(
            final ImageView imageView,
            final boolean adjustViewBounds
    ) {
        ImageViewUtils.setAdjustViewBounds(imageView, adjustViewBounds);
        return this;
    }

    /**
     * 设置 ImageView 最大高度
     * @param imageView ImageView
     * @param maxHeight 最大高度
     * @return {@link ViewHelper}
     */
    public ViewHelper setMaxHeight(
            final ImageView imageView,
            final int maxHeight
    ) {
        ImageViewUtils.setMaxHeight(imageView, maxHeight);
        return this;
    }

    /**
     * 设置 ImageView 最大宽度
     * @param imageView ImageView
     * @param maxWidth  最大宽度
     * @return {@link ViewHelper}
     */
    public ViewHelper setMaxWidth(
            final ImageView imageView,
            final int maxWidth
    ) {
        ImageViewUtils.setMaxWidth(imageView, maxWidth);
        return this;
    }

    /**
     * 设置 ImageView Level
     * @param view  {@link View}
     * @param level level Image
     * @return {@link ViewHelper}
     */
    public ViewHelper setImageLevel(
            final View view,
            final int level
    ) {
        ImageViewUtils.setImageLevel(view, level);
        return this;
    }

    /**
     * 设置 ImageView Bitmap
     * @param view   {@link View}
     * @param bitmap {@link Bitmap}
     * @return {@link ViewHelper}
     */
    public ViewHelper setImageBitmap(
            final View view,
            final Bitmap bitmap
    ) {
        ImageViewUtils.setImageBitmap(view, bitmap);
        return this;
    }

    /**
     * 设置 ImageView Drawable
     * @param view     {@link View}
     * @param drawable {@link Bitmap}
     * @return {@link ViewHelper}
     */
    public ViewHelper setImageDrawable(
            final View view,
            final Drawable drawable
    ) {
        ImageViewUtils.setImageDrawable(view, drawable);
        return this;
    }

    /**
     * 设置 ImageView 资源
     * @param view  {@link View}
     * @param resId resource identifier
     * @return {@link ViewHelper}
     */
    public ViewHelper setImageResource(
            final View view,
            @DrawableRes final int resId
    ) {
        ImageViewUtils.setImageResource(view, resId);
        return this;
    }

    /**
     * 设置 ImageView Matrix
     * @param view   {@link View}
     * @param matrix {@link Matrix}
     * @return {@link ViewHelper}
     */
    public ViewHelper setImageMatrix(
            final View view,
            final Matrix matrix
    ) {
        ImageViewUtils.setImageMatrix(view, matrix);
        return this;
    }

    /**
     * 设置 ImageView 着色颜色
     * @param view {@link View}
     * @param tint 着色颜色
     * @return {@link ViewHelper}
     */
    public ViewHelper setImageTintList(
            final View view,
            final ColorStateList tint
    ) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ImageViewUtils.setImageTintList(view, tint);
        }
        return this;
    }

    /**
     * 设置 ImageView 着色模式
     * @param view     {@link View}
     * @param tintMode 着色模式 {@link PorterDuff.Mode}
     * @return {@link ViewHelper}
     */
    public ViewHelper setImageTintMode(
            final View view,
            final PorterDuff.Mode tintMode
    ) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ImageViewUtils.setImageTintMode(view, tintMode);
        }
        return this;
    }

    /**
     * 设置 ImageView 缩放类型
     * @param view      {@link View}
     * @param scaleType 缩放类型 {@link ImageView.ScaleType}
     * @return {@link ViewHelper}
     */
    public ViewHelper setScaleType(
            final View view,
            final ImageView.ScaleType scaleType
    ) {
        ImageViewUtils.setScaleType(view, scaleType);
        return this;
    }

    /**
     * 设置 View 图片资源
     * @param resId resource identifier
     * @param views View[]
     * @return {@link ViewHelper}
     */
    public ViewHelper setBackgroundResources(
            @DrawableRes final int resId,
            final View... views
    ) {
        ImageViewUtils.setBackgroundResources(resId, views);
        return this;
    }

    /**
     * 设置 View 图片资源
     * @param resId        resource identifier
     * @param isVisibility {@link View#VISIBLE}、{@link View#INVISIBLE}、{@link View#GONE}
     * @param views        View[]
     * @return {@link ViewHelper}
     */
    public ViewHelper setBackgroundResources(
            @DrawableRes final int resId,
            final int isVisibility,
            final View... views
    ) {
        ImageViewUtils.setBackgroundResources(resId, isVisibility, views);
        return this;
    }

    /**
     * 设置 View 图片资源
     * @param resId resource identifier
     * @param views View[]
     * @return {@link ViewHelper}
     */
    public ViewHelper setImageResources(
            @DrawableRes final int resId,
            final View... views
    ) {
        ImageViewUtils.setImageResources(resId, views);
        return this;
    }

    /**
     * 设置 View 图片资源
     * @param resId        resource identifier
     * @param isVisibility {@link View#VISIBLE}、{@link View#INVISIBLE}、{@link View#GONE}
     * @param views        View[]
     * @return {@link ViewHelper}
     */
    public ViewHelper setImageResources(
            @DrawableRes final int resId,
            final int isVisibility,
            final View... views
    ) {
        ImageViewUtils.setImageResources(resId, isVisibility, views);
        return this;
    }

    /**
     * 设置 View Bitmap
     * @param bitmap {@link Bitmap}
     * @param views  View[]
     * @return {@link ViewHelper}
     */
    public ViewHelper setImageBitmaps(
            final Bitmap bitmap,
            final View... views
    ) {
        ImageViewUtils.setImageBitmaps(bitmap, views);
        return this;
    }

    /**
     * 设置 View Bitmap
     * @param bitmap       {@link Bitmap}
     * @param isVisibility {@link View#VISIBLE}、{@link View#INVISIBLE}、{@link View#GONE}
     * @param views        View[]
     * @return {@link ViewHelper}
     */
    public ViewHelper setImageBitmaps(
            final Bitmap bitmap,
            final int isVisibility,
            final View... views
    ) {
        ImageViewUtils.setImageBitmaps(bitmap, isVisibility, views);
        return this;
    }

    /**
     * 设置 View Drawable
     * @param drawable {@link drawable}
     * @param views    View[]
     * @return {@link ViewHelper}
     */
    public ViewHelper setImageDrawables(
            final Drawable drawable,
            final View... views
    ) {
        ImageViewUtils.setImageDrawables(drawable, views);
        return this;
    }

    /**
     * 设置 View Drawable
     * @param drawable     {@link drawable}
     * @param isVisibility {@link View#VISIBLE}、{@link View#INVISIBLE}、{@link View#GONE}
     * @param views        View[]
     * @return {@link ViewHelper}
     */
    public ViewHelper setImageDrawables(
            final Drawable drawable,
            final int isVisibility,
            final View... views
    ) {
        ImageViewUtils.setImageDrawables(drawable, isVisibility, views);
        return this;
    }

    /**
     * 设置 View 缩放模式
     * @param scaleType {@link ImageView.ScaleType}
     * @param views     View[]
     * @return {@link ViewHelper}
     */
    public ViewHelper setScaleTypes(
            final ImageView.ScaleType scaleType,
            final View... views
    ) {
        ImageViewUtils.setScaleTypes(scaleType, views);
        return this;
    }

    /**
     * 设置 View 缩放模式
     * @param scaleType    {@link ImageView.ScaleType}
     * @param isVisibility {@link View#VISIBLE}、{@link View#INVISIBLE}、{@link View#GONE}
     * @param views        View[]
     * @return {@link ViewHelper}
     */
    public ViewHelper setScaleTypes(
            final ImageView.ScaleType scaleType,
            final int isVisibility,
            final View... views
    ) {
        ImageViewUtils.setScaleTypes(scaleType, isVisibility, views);
        return this;
    }

    // ========
    // = View =
    // ========

    /**
     * 设置 CompoundDrawables Padding
     * @param textView {@link TextView}
     * @param padding  CompoundDrawables Padding
     * @return {@link ViewHelper}
     */
    public ViewHelper setCompoundDrawablePadding(
            final TextView textView,
            final int padding
    ) {
        TextViewUtils.setCompoundDrawablePadding(textView, padding);
        return this;
    }

    /**
     * 设置 Left CompoundDrawables
     * @param textView {@link TextView}
     * @param left     left Drawable
     * @return {@link ViewHelper}
     */
    public ViewHelper setCompoundDrawablesByLeft(
            final TextView textView,
            final Drawable left
    ) {
        TextViewUtils.setCompoundDrawablesByLeft(textView, left);
        return this;
    }

    /**
     * 设置 Top CompoundDrawables
     * @param textView {@link TextView}
     * @param top      top Drawable
     * @return {@link ViewHelper}
     */
    public ViewHelper setCompoundDrawablesByTop(
            final TextView textView,
            final Drawable top
    ) {
        TextViewUtils.setCompoundDrawablesByTop(textView, top);
        return this;
    }

    /**
     * 设置 Right CompoundDrawables
     * @param textView {@link TextView}
     * @param right    right Drawable
     * @return {@link ViewHelper}
     */
    public ViewHelper setCompoundDrawablesByRight(
            final TextView textView,
            final Drawable right
    ) {
        TextViewUtils.setCompoundDrawablesByRight(textView, right);
        return this;
    }

    /**
     * 设置 Bottom CompoundDrawables
     * @param textView {@link TextView}
     * @param bottom   bottom Drawable
     * @return {@link ViewHelper}
     */
    public ViewHelper setCompoundDrawablesByBottom(
            final TextView textView,
            final Drawable bottom
    ) {
        TextViewUtils.setCompoundDrawablesByBottom(textView, bottom);
        return this;
    }

    /**
     * 设置 CompoundDrawables
     * <pre>
     *     CompoundDrawable 的大小控制是通过 drawable.setBounds() 控制
     *     需要先设置 Drawable 的 setBounds
     *     {@link dev.utils.app.image.ImageUtils#setBounds}
     * </pre>
     * @param textView {@link TextView}
     * @param left     left Drawable
     * @param top      top Drawable
     * @param right    right Drawable
     * @param bottom   bottom Drawable
     * @return {@link ViewHelper}
     */
    public ViewHelper setCompoundDrawables(
            final TextView textView,
            final Drawable left,
            final Drawable top,
            final Drawable right,
            final Drawable bottom
    ) {
        TextViewUtils.setCompoundDrawables(textView, left, top, right, bottom);
        return this;
    }

    /**
     * 设置 Left CompoundDrawables ( 按照原有比例大小显示图片 )
     * @param textView {@link TextView}
     * @param left     left Drawable
     * @return {@link ViewHelper}
     */
    public ViewHelper setCompoundDrawablesWithIntrinsicBoundsByLeft(
            final TextView textView,
            final Drawable left
    ) {
        TextViewUtils.setCompoundDrawablesWithIntrinsicBoundsByLeft(textView, left);
        return this;
    }

    /**
     * 设置 Top CompoundDrawables ( 按照原有比例大小显示图片 )
     * @param textView {@link TextView}
     * @param top      top Drawable
     * @return {@link ViewHelper}
     */
    public ViewHelper setCompoundDrawablesWithIntrinsicBoundsByTop(
            final TextView textView,
            final Drawable top
    ) {
        TextViewUtils.setCompoundDrawablesWithIntrinsicBoundsByTop(textView, top);
        return this;
    }

    /**
     * 设置 Right CompoundDrawables ( 按照原有比例大小显示图片 )
     * @param textView {@link TextView}
     * @param right    right Drawable
     * @return {@link ViewHelper}
     */
    public ViewHelper setCompoundDrawablesWithIntrinsicBoundsByRight(
            final TextView textView,
            final Drawable right
    ) {
        TextViewUtils.setCompoundDrawablesWithIntrinsicBoundsByRight(textView, right);
        return this;
    }

    /**
     * 设置 Bottom CompoundDrawables ( 按照原有比例大小显示图片 )
     * @param textView {@link TextView}
     * @param bottom   bottom Drawable
     * @return {@link ViewHelper}
     */
    public ViewHelper setCompoundDrawablesWithIntrinsicBoundsByBottom(
            final TextView textView,
            final Drawable bottom
    ) {
        TextViewUtils.setCompoundDrawablesWithIntrinsicBoundsByBottom(textView, bottom);
        return this;
    }

    /**
     * 设置 CompoundDrawables ( 按照原有比例大小显示图片 )
     * @param textView {@link TextView}
     * @param left     left Drawable
     * @param top      top Drawable
     * @param right    right Drawable
     * @param bottom   bottom Drawable
     * @return {@link ViewHelper}
     */
    public ViewHelper setCompoundDrawablesWithIntrinsicBounds(
            final TextView textView,
            final Drawable left,
            final Drawable top,
            final Drawable right,
            final Drawable bottom
    ) {
        TextViewUtils.setCompoundDrawablesWithIntrinsicBounds(textView, left, top, right, bottom);
        return this;
    }

    // =================
    // = ListViewUtils =
    // =================

    /**
     * 滑动到指定索引 ( 有滚动过程 )
     * @param view     {@link View}
     * @param position 索引
     * @return {@link ViewHelper}
     */
    public ViewHelper smoothScrollToPosition(
            final View view,
            final int position
    ) {
        ListViewUtils.smoothScrollToPosition(view, position);
        return this;
    }

    /**
     * 滑动到指定索引 ( 无滚动过程 )
     * @param view     {@link View}
     * @param position 索引
     * @return {@link ViewHelper}
     */
    public ViewHelper scrollToPosition(
            final View view,
            final int position
    ) {
        ListViewUtils.scrollToPosition(view, position);
        return this;
    }

    // ============
    // = 滑动到顶部 =
    // ============

    /**
     * 滑动到顶部 ( 有滚动过程 )
     * @param view {@link View}
     * @return {@link ViewHelper}
     */
    public ViewHelper smoothScrollToTop(final View view) {
        ListViewUtils.smoothScrollToTop(view);
        return this;
    }

    /**
     * 滑动到顶部 ( 无滚动过程 )
     * @param view {@link View}
     * @return {@link ViewHelper}
     */
    public ViewHelper scrollToTop(final View view) {
        ListViewUtils.scrollToTop(view);
        return this;
    }

    // ============
    // = 滑动到底部 =
    // ============

    /**
     * 滑动到底部 ( 有滚动过程 )
     * <pre>
     *     如果未到达底部 ( position 可以再加上 smoothScrollBy 搭配到底部 )
     *     smoothScrollToBottom(view)
     *     smoothScrollBy(view, 0, Integer.MAX_VALUE);
     * </pre>
     * @param view {@link View}
     * @return {@link ViewHelper}
     */
    public ViewHelper smoothScrollToBottom(final View view) {
        ListViewUtils.smoothScrollToBottom(view);
        return this;
    }

    /**
     * 滑动到底部 ( 无滚动过程 )
     * <pre>
     *     如果未到达底部 ( position 可以再加上 scrollBy 搭配到底部 )
     *     scrollToBottom(view)
     *     scrollBy(view, 0, Integer.MAX_VALUE);
     * </pre>
     * @param view {@link View}
     * @return {@link ViewHelper}
     */
    public ViewHelper scrollToBottom(final View view) {
        ListViewUtils.scrollToBottom(view);
        return this;
    }

    // ==============
    // = ScrollView =
    // ==============

    /**
     * 滚动到指定位置 ( 有滚动过程, 相对于初始位置移动 )
     * @param view {@link View}
     * @param x    X 轴开始坐标
     * @param y    Y 轴开始坐标
     * @return {@link ViewHelper}
     */
    public ViewHelper smoothScrollTo(
            final View view,
            final int x,
            final int y
    ) {
        ListViewUtils.smoothScrollTo(view, x, y);
        return this;
    }

    /**
     * 滚动到指定位置 ( 有滚动过程, 相对于上次移动的最后位置移动 )
     * @param view {@link View}
     * @param x    X 轴开始坐标
     * @param y    Y 轴开始坐标
     * @return {@link ViewHelper}
     */
    public ViewHelper smoothScrollBy(
            final View view,
            final int x,
            final int y
    ) {
        ListViewUtils.smoothScrollBy(view, x, y);
        return this;
    }

    /**
     * 滚动方向 ( 有滚动过程 )
     * @param view      {@link View}
     * @param direction 滚动方向 如: View.FOCUS_UP、View.FOCUS_DOWN
     * @return {@link ViewHelper}
     */
    public ViewHelper fullScroll(
            final View view,
            final int direction
    ) {
        ListViewUtils.fullScroll(view, direction);
        return this;
    }
}